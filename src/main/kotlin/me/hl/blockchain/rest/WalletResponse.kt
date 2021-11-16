package me.hl.blockchain.rest

data class WalletResponse(
    val balance: Long? = 0,
    val privateKey: String,
    val publicKey: String
)
