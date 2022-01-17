package me.hl.blockchain.domain.transaction

data class Transaction(
    var amount: Long,
    var senderPublicKey: String,
    var receiverPublicKey: String
)