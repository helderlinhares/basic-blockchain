package me.hl.blockchain.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.hl.blockchain.domain.Block
import me.hl.blockchain.domain.Chain
import me.hl.blockchain.rest.BlockResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChainService(val chain: Chain) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun getBalance(publicKey: String): Long {
        return chain.blocks
            .filter { it.transaction.receiverPublicKey == publicKey }
            .sumOf { it.transaction.amount }
    }

    fun mine(amount: Long): Long {
        logger.info("[Mining][Amount:$amount]...")
        return someAlgorithm(amount).also {
            logger.info("[Solved][Result:$it]")
        }
    }

    fun addBlockOnChain(block: Block) = chain.blocks.add(block)

    fun getLastBlockHash() = chain.blocks.last().hash()

    fun getChainBlocks(): MutableList<BlockResponse> {
        val blocks: MutableList<BlockResponse> = ArrayList()
        for (block in chain.blocks) {
            blocks.add(
                BlockResponse(
                    id = block.hash(),
                    previousBlockHash = block.previousBlockHash,
                    transaction = block.transaction,
                    time = block.time.toString()
                )
            )
        }
        return blocks
    }

    private fun someAlgorithm(amount: Long): Long {
        return runBlocking {
            launch {
                delay(amount)
            }.let { amount }
        }
    }
}
