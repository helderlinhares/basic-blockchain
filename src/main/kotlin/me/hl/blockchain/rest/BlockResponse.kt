package me.hl.blockchain.rest

import me.hl.blockchain.domain.Transaction

data class BlockResponse(
    var id: String,
    var previousBlockHash: String?,
    var transaction: Transaction,
    var time: String
)