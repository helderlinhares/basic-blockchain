package me.hl.blockchain.service

import me.hl.blockchain.Commons
import me.hl.blockchain.Commons.VALID_PRIVATE_KEY
import me.hl.blockchain.Commons.VALID_PUBLIC_KEY
import me.hl.blockchain.buildValidTransactionResponse
import me.hl.blockchain.domain.chain.ChainService
import me.hl.blockchain.domain.transaction.TransactionService
import me.hl.blockchain.shared.InvalidAmountException
import me.hl.blockchain.shared.InvalidKeyException
import me.hl.blockchain.shared.KeyConverter.toPrivateKey
import me.hl.blockchain.shared.KeyConverter.toPublicKey
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionServiceTest {
    @MockBean
    private lateinit var chainService: ChainService

    @Autowired
    private lateinit var transactionService: TransactionService

    @Test
    fun `Should return transaction When mining with a valid private key`() {
        val miningAmount = 1L
        val validTransactionResponse = buildValidTransactionResponse(miningAmount)
        transactionService.mineBlock(miningAmount, VALID_PRIVATE_KEY.toPrivateKey()).also {
            assertThat(it).usingRecursiveComparison().isEqualTo(validTransactionResponse)
        }
    }

    @Test(expected = InvalidKeyException::class)
    fun `Should not return transaction When mining with an invalid private key`() {
        val miningAmount = 1L
        transactionService.mineBlock(miningAmount, Commons.INVALID_PRIVATE_KEY.toPrivateKey())
    }

    @Test(expected = InvalidAmountException::class)
    fun `Should not return transaction When mining with an negative amount`() {
        val miningAmount = -1L
        transactionService.mineBlock(miningAmount, VALID_PRIVATE_KEY.toPrivateKey())
    }

    @Test(expected = InvalidAmountException::class)
    fun `Should not return transaction When mining with a zero amount`() {
        val miningAmount = 0L
        transactionService.mineBlock(miningAmount, VALID_PRIVATE_KEY.toPrivateKey())
    }

    @Test
    fun `Should return transaction When sending coin with valid private key and public key`() {
        val amount = 1L
        BDDMockito.given(chainService.getBalance(VALID_PUBLIC_KEY)).willReturn(amount)
        val validTransactionResponse = buildValidTransactionResponse(amount)

        transactionService.run{
            sendCoin(amount, VALID_PRIVATE_KEY.toPrivateKey(), VALID_PUBLIC_KEY.toPublicKey()).also{
                assertThat(it).usingRecursiveComparison().isEqualTo(validTransactionResponse)
            }
        }
    }

    @Test(expected = InvalidKeyException::class)
    fun `Should not return transaction When sending coin with an invalid private key`() {
        val sendingAmount = 1L
        transactionService.sendCoin(sendingAmount, Commons.INVALID_PRIVATE_KEY.toPrivateKey(), VALID_PUBLIC_KEY.toPublicKey())
    }

    @Test(expected = InvalidKeyException::class)
    fun `Should not return transaction When sending coin with an invalid public key`() {
        val sendingAmount = 1L
        transactionService.sendCoin(sendingAmount, VALID_PRIVATE_KEY.toPrivateKey(), Commons.INVALID_PUBLIC_KEY.toPublicKey())
    }

    @Test(expected = InvalidAmountException::class)
    fun `Should not return transaction When sending coin without credit`() {
        val sendingAmount = 1L
        transactionService.sendCoin(sendingAmount, VALID_PRIVATE_KEY.toPrivateKey(), VALID_PUBLIC_KEY.toPublicKey())
    }

    @Test(expected = InvalidAmountException::class)
    fun `Should not return transaction When sending coin with an negative amount`() {
        val sendingAmount = -1L
        transactionService.sendCoin(sendingAmount, VALID_PRIVATE_KEY.toPrivateKey(), VALID_PUBLIC_KEY.toPublicKey())

    }

    @Test(expected = InvalidAmountException::class)
    fun `Should not return transaction When sending coin with a zero amount`() {
        val sendingAmount = 0L
        transactionService.sendCoin(sendingAmount, VALID_PRIVATE_KEY.toPrivateKey(), VALID_PUBLIC_KEY.toPublicKey())
    }

}