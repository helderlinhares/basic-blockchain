package me.hl.blockchain.domain

import me.hl.blockchain.util.Converter.toHex
import java.security.MessageDigest
import java.util.Calendar

data class Block(
    var previousBlockHash: String?,
    var transaction: Transaction,
    var time: Long = Calendar.getInstance().timeInMillis
) {
    fun hash(): String {
        val md = MessageDigest.getInstance("SHA-256")
        val messageHash = this.toString().toByteArray()
        return md.digest(messageHash).toHex()
    }
}