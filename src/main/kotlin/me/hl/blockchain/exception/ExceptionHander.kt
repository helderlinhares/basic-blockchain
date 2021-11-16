package me.hl.blockchain.exception

import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler(val messageSource: MessageSource) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(InvalidAmountException::class)
    fun handleInvalidAmountException(
        exception: InvalidAmountException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return buildResponseAndLog(
            HttpStatus.BAD_REQUEST,
            request,
            ErrorResponse(
                listOf(
                    Error(
                        code = exception.message, message = messageSource.getMessage(
                            exception.message, null, request.locale
                        )
                    )
                )
            )
        )
    }

    @ExceptionHandler(InvalidKeyException::class)
    fun handleInvalidKeyException(
        exception: InvalidKeyException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return buildResponseAndLog(
            HttpStatus.BAD_REQUEST,
            request,
            ErrorResponse(
                listOf(
                    Error(
                        code = exception.message, message = messageSource.getMessage(
                            exception.message, null, request.locale
                        )
                    )
                )
            )
        )
    }

    @ExceptionHandler(MissingRequestHeaderException::class)
    fun handleMissingRequestHeaderException(
        exception: MissingRequestHeaderException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return buildResponseAndLog(
            HttpStatus.BAD_REQUEST,
            request,
            ErrorResponse(
                listOf(
                    Error(
                        code = ErrorCode.INVALID_REQUEST_HEADER, message = messageSource.getMessage(
                            ErrorCode.INVALID_REQUEST_HEADER, arrayOf(exception.headerName), request.locale
                        )
                    )
                )
            )
        )
    }

    private fun buildResponseAndLog(
        status: HttpStatus,
        request: HttpServletRequest,
        payload: ErrorResponse? = null
    ): ResponseEntity<ErrorResponse> {
        logResponse(status, payload, request)
        return ResponseEntity.status(status).body(payload)
    }

    private fun logResponse(status: HttpStatus, errorResponse: ErrorResponse?, request: HttpServletRequest) {
        val logString =
            "statusCode=${status.value()}, response=$errorResponse, requestPath=${request.requestURI}, methodValue=${request.method}"
        if (status.is5xxServerError)
            logger.error(logString)
        else
            logger.info(logString)
    }

}
