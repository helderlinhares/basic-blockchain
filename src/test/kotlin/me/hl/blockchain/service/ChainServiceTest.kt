package me.hl.blockchain.service

import me.hl.blockchain.domain.block.Block
import me.hl.blockchain.domain.chain.Chain
import me.hl.blockchain.domain.chain.ChainService
import me.hl.blockchain.domain.transaction.Transaction
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class ChainServiceTest {

    @Autowired
    private lateinit var chainService: ChainService

    @Test
    fun `Should return correct balance When getting balance`() {
        chainService.getBalance(chainService.chain.GENESIS_RECEIVER_KEY).also{
            assertThat(it).isEqualTo(chainService.chain.GENESIS_AMOUNT)
        }
    }

    @Test
    fun `Should add block on chain`() {
        val newBlock = Block(
            Transaction(
                2L,
                "senderTwo",
                "receiverTwo"
            )
        )
        chainService.addBlockOnChain(newBlock).also{
            assertThat(it).isEqualTo(true)
        }
    }

    @Test
    fun `Should return amount When mining`() {
        val mineAmount = 5L
        chainService.mine(mineAmount).also{
            assertThat(it).isEqualTo(mineAmount)
        }
    }


    @Test
    fun `Should return correct balance When getting balance after adding a positive block`() {
        val originalBalance = chainService.getBalance(chainService.chain.GENESIS_RECEIVER_KEY)
        val positiveAmount = -chainService.chain.GENESIS_AMOUNT
        val finalBalance = originalBalance + positiveAmount

        val newPositiveAmountBlock = Block(
            Transaction(
                positiveAmount,
                "senderOne",
                chainService.chain.GENESIS_RECEIVER_KEY
            )
        )
        chainService.addBlockOnChain(newPositiveAmountBlock)
        chainService.getBalance(chainService.chain.GENESIS_RECEIVER_KEY).also {
            assertThat(it).isEqualTo(finalBalance)
        }
    }

    @Test
    fun `Should return correct balance When getting balance after adding a negative block`() {
        val originalBalance = chainService.getBalance(chainService.chain.GENESIS_RECEIVER_KEY)
        val negativeAmount = -chainService.chain.GENESIS_AMOUNT
        val finalBalance = originalBalance + negativeAmount
        val newNegativeAmountBlock = Block(
            Transaction(
                negativeAmount,
                chainService.chain.GENESIS_RECEIVER_KEY,
                chainService.chain.GENESIS_RECEIVER_KEY
            )
        )
        chainService.addBlockOnChain(newNegativeAmountBlock)
        chainService.getBalance(chainService.chain.GENESIS_RECEIVER_KEY).also {
            assertThat(it).isEqualTo(finalBalance)
        }
    }

    @Test
    fun `Should return genesis block When getting chain blocks`() {
        chainService.getChainBlocks().also{
            assertThat(it[0].transaction)
                .hasFieldOrPropertyWithValue("senderPublicKey", Chain.GENESIS_SENDER_KEY)
        }
    }

}