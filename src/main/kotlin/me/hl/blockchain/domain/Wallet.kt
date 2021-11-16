package me.hl.blockchain.domain

import java.security.PrivateKey
import java.security.PublicKey

data class Wallet(
    var privateKey: PrivateKey,
    var publicKey: PublicKey
)
