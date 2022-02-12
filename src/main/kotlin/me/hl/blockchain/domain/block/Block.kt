package me.hl.blockchain.domain.block

import me.hl.blockchain.domain.transaction.Transaction
import me.hl.blockchain.shared.KeyConverter.toHex
import java.security.MessageDigest
import java.util.Calendar

data class Block(
    var transaction: Transaction,
    var time: Long = Calendar.getInstance().timeInMillis
) {
    fun hash(): String {
        val md = MessageDigest.getInstance("SHA-256")
        val messageHash = this.toString().toByteArray()
        return md.digest(messageHash).toHex()
    }
}