package me.hl.blockchain.domain

import org.springframework.stereotype.Component

@Component
object Chain{
    const val GENESIS_AMOUNT = 1L
    const val GENESIS_RECEIVER_KEY = "helderlinhares"
    private const val GENESIS_SENDER_KEY = "GENESIS"

    val blocks: MutableList<Block> = ArrayList()
    init {
        blocks.add(
            Block(
                null,
                Transaction(GENESIS_AMOUNT, GENESIS_SENDER_KEY, GENESIS_RECEIVER_KEY)
            )
        )
    }
}