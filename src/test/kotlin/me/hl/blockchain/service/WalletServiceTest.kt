package me.hl.blockchain.service

import me.hl.blockchain.Commons.INITIAL_BALANCE
import me.hl.blockchain.Commons.INVALID_PRIVATE_KEY
import me.hl.blockchain.Commons.VALID_PRIVATE_KEY
import me.hl.blockchain.buildValidWalletResponse
import me.hl.blockchain.exception.InvalidKeyException
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class WalletServiceTest {

    @Autowired
    private lateinit var walletService: WalletService

    @Test
    fun `Should return wallet When creating it`() {
        walletService.createWallet().also {
            assertThat(it).hasFieldOrPropertyWithValue("balance", INITIAL_BALANCE)
            assertThat(it).hasFieldOrProperty("privateKey")
            assertThat(it).hasFieldOrProperty("publicKey")
        }
    }

    @Test
    fun `Should return wallet When opening with a valid private key`() {
        val validWallet = buildValidWalletResponse(INITIAL_BALANCE)
        walletService.openWallet(VALID_PRIVATE_KEY).also {
            assertThat(it).usingRecursiveComparison().isEqualTo(validWallet)
        }
    }

    @Test(expected = InvalidKeyException::class)
    fun `Should not return wallet When opening with an invalid private key`() {
        walletService.openWallet(INVALID_PRIVATE_KEY)
    }

}