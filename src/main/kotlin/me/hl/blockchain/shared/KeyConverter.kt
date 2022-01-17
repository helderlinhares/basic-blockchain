package me.hl.blockchain.shared

import org.springframework.util.Base64Utils
import java.security.Key
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.RSAPrivateCrtKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAPublicKeySpec
import java.security.spec.X509EncodedKeySpec

object KeyConverter {

    fun String.toPrivateKey(): PrivateKey =
        try {
            Base64Utils.decodeFromString(this)
                .let { decoded ->
                    PKCS8EncodedKeySpec(decoded).let { spec ->
                        KeyFactory.getInstance("RSA")
                            .generatePrivate(spec)
                    }
                }
        } catch (ex: Exception) {
            throw InvalidKeyException(ErrorCode.INVALID_KEY)
        }

    fun String.toPublicKey(): PublicKey =
        try {
            Base64Utils.decodeFromString(this)
                .let { decoded ->
                    X509EncodedKeySpec(decoded).let { spec ->
                        KeyFactory.getInstance("RSA")
                            .generatePublic(spec)
                    }
                }
        } catch (ex: Exception) {
            throw InvalidKeyException(ErrorCode.INVALID_KEY)
        }

    fun Key.toPem() =
        try {
            Base64Utils.encodeToString(this.encoded)
                .chunked(64)
                .joinToString(separator = "")
        } catch (ex: Exception) {
            throw InvalidKeyException(ErrorCode.INVALID_KEY)
        }

    fun RSAPrivateCrtKey.recoveryPublicKey(): PublicKey =
        try {
            RSAPublicKeySpec(this.modulus, this.publicExponent)
                .let { publicKeySpec ->
                    KeyFactory.getInstance("RSA")
                        .generatePublic(publicKeySpec)
                }
        } catch (ex: Exception) {
            throw InvalidKeyException(ErrorCode.INVALID_KEY)
        }

    fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

}