package me.hl.blockchain.domain.transaction

import me.hl.blockchain.domain.block.Block
import me.hl.blockchain.domain.chain.ChainService
import me.hl.blockchain.shared.ErrorCode
import me.hl.blockchain.shared.InvalidAmountException
import me.hl.blockchain.shared.InvalidKeyException
import me.hl.blockchain.shared.KeyConverter.recoveryPublicKey
import me.hl.blockchain.shared.KeyConverter.toPem
import org.springframework.stereotype.Service
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.interfaces.RSAPrivateCrtKey

@Service
class TransactionService(val chainService: ChainService) {

    fun mineBlock(amount: Long, privateKey: PrivateKey): Transaction {
        if (amount > 0) {
            val publicKey = (privateKey as RSAPrivateCrtKey).recoveryPublicKey()
            val transaction = Transaction(amount, publicKey.toPem(), publicKey.toPem())
            val digitalSignature = signTransaction(transaction, privateKey)

            val isValidSignature = isValidSignature(transaction, publicKey, digitalSignature)

            if (isValidSignature) {
                chainService.run {
                    mine(transaction.amount)
                    addBlockOnChain(
                        Block(chainService.getLastBlockHash(), transaction)
                    )
                }
            }
            return transaction
        } else
            throw InvalidAmountException(ErrorCode.INVALID_AMOUNT)
    }

    fun sendCoin(amount: Long, senderPrivateKey: PrivateKey, receiverPublicKey: PublicKey): Transaction {
        val senderPublicKey = (senderPrivateKey as RSAPrivateCrtKey).recoveryPublicKey()
        val senderPublicPemKey = senderPublicKey.toPem()

        if (amount > 0 && chainService.getBalance(senderPublicPemKey) >= amount) {
            val debitTransaction = Transaction(-amount, senderPublicPemKey, senderPublicPemKey)
            val creditTransaction = Transaction(amount, senderPublicPemKey, receiverPublicKey.toPem())

            val digitalDebitSignature = signTransaction(debitTransaction, senderPrivateKey)
            val digitalCreditSignature = signTransaction(creditTransaction, senderPrivateKey)

            transferCoin(
                debitTransaction,
                creditTransaction,
                senderPublicKey,
                digitalDebitSignature,
                digitalCreditSignature
            )

            return creditTransaction
        } else
            throw InvalidAmountException(ErrorCode.INVALID_AMOUNT)

    }

    private fun signTransaction(transaction: Transaction, senderPrivateKey: PrivateKey): ByteArray {
        val messageHash = transaction.toString().toByteArray()

        val signature: Signature = Signature.getInstance("SHA256withRSA")
        signature.initSign(senderPrivateKey)
        signature.update(messageHash)

        return signature.sign()
    }

    private fun isValidSignature(transaction: Transaction, senderPublicKey: PublicKey, signature: ByteArray): Boolean {
        val chainSignature = Signature.getInstance("SHA256withRSA")

        return chainSignature
            .apply {
                chainSignature.initVerify(senderPublicKey)
                val messageHash = transaction.toString().toByteArray()
                this.update(messageHash)
            }.let {
                chainSignature.verify(signature)
            }
    }

    private fun transferCoin(
        debitTransaction: Transaction,
        creditTransaction: Transaction,
        senderPublicKey: PublicKey,
        debitSignature: ByteArray,
        creditSignature: ByteArray
    ) {
        val isValidDebtSignature = isValidSignature(debitTransaction, senderPublicKey, debitSignature)
        val isValidCreditSignature = isValidSignature(creditTransaction, senderPublicKey, creditSignature)

        if (isValidDebtSignature && isValidCreditSignature) {
            val debitBlock = Block(chainService.getLastBlockHash(), debitTransaction)
            chainService.addBlockOnChain(debitBlock)
            val newCreditBlock = Block(chainService.getLastBlockHash(), creditTransaction)
            chainService.addBlockOnChain(newCreditBlock)
        } else
            throw InvalidKeyException(ErrorCode.INVALID_KEY)
    }

}