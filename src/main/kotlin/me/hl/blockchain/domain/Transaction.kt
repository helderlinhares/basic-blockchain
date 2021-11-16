package me.hl.blockchain.domain

data class Transaction(
    var amount: Long,
    var senderPublicKey: String,
    var receiverPublicKey: String
)