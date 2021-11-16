package me.hl.blockchain.exception

data class Error(val code: String?, val message: String?)
data class ErrorResponse(val errors: List<Error>)

class InvalidAmountException(override val message: String) : RuntimeException()
class InvalidKeyException(override val message: String) : RuntimeException()

object ErrorCode {
    const val INVALID_AMOUNT = "invalid_amount"
    const val INVALID_KEY = "invalid_key"
    const val INVALID_REQUEST_HEADER = "invalid_request_header"
}
