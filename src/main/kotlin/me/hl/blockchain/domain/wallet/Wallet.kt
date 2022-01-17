package me.hl.blockchain.domain.wallet

import java.security.PrivateKey
import java.security.PublicKey

data class Wallet(
    var privateKey: PrivateKey,
    var publicKey: PublicKey
)
