package me.hl.blockchain.service

import me.hl.blockchain.domain.Wallet
import me.hl.blockchain.rest.WalletResponse
import me.hl.blockchain.util.Converter.recoveryPublicKey
import me.hl.blockchain.util.Converter.toPem
import me.hl.blockchain.util.Converter.toPrivateKey
import org.springframework.stereotype.Service
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateCrtKey

@Service
class WalletService(val chainService: ChainService) {

    fun createWallet(): WalletResponse {
        val keyPair: KeyPair = generateKeyPair()
        return Wallet(keyPair.private, keyPair.public).toResponse()
    }

    fun openWallet(privatePemKey: String): WalletResponse {
        val privateKey = privatePemKey.toPrivateKey()
        val publicKey = (privateKey as RSAPrivateCrtKey).recoveryPublicKey()
        return Wallet(privateKey, publicKey).toResponse()
    }

    private fun getBalance(publicPemKey: String) = chainService.getBalance(publicPemKey)

    private fun generateKeyPair() = KeyPairGenerator.getInstance("RSA")
        .let {
            it.initialize(2048)
            it.generateKeyPair()
        }

    private fun Wallet.toResponse() = WalletResponse(
        getBalance(this.publicKey.toPem()),
        this.privateKey.toPem(),
        this.publicKey.toPem()
    )

}
