package me.hl.blockchain.domain.chain

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.hl.blockchain.domain.block.Block
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChainService(val chain: Chain) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun getBalance(publicKey: String) =
        chain.blocks
            .filter { it.transaction.receiverPublicKey == publicKey }
            .sumOf { it.transaction.amount }

    fun mine(amount: Long): Long {
        logger.info("[Mining][Amount:$amount]...")
        return someAlgorithm(amount).also {
            logger.info("[Solved][Result:$it]")
        }
    }

    fun addBlockOnChain(block: Block) = chain.blocks.add(block)

    fun getChainBlocks() = chain.blocks

    private fun someAlgorithm(amount: Long) =
        runBlocking {
            launch {
                delay(amount)
            }.let { amount }
        }
}
