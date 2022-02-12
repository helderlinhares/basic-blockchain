package me.hl.blockchain.rest

import me.hl.blockchain.domain.transaction.Transaction

data class BlockResponse(
    var previousId: Int,
    var id: Int,
    var transaction: Transaction,
    var time: Long,
    var hash: String
)
