package me.hl.blockchain

import me.hl.blockchain.Commons.VALID_PRIVATE_KEY
import me.hl.blockchain.Commons.VALID_PUBLIC_KEY
import me.hl.blockchain.domain.transaction.Transaction
import me.hl.blockchain.shared.Error
import me.hl.blockchain.shared.ErrorCode
import me.hl.blockchain.shared.ErrorResponse
import me.hl.blockchain.rest.BlockResponse
import me.hl.blockchain.rest.WalletResponse
import org.springframework.context.MessageSource
import java.util.Calendar
import java.util.Locale


fun buildValidWalletResponse(balance: Long) = WalletResponse(
    balance,
    VALID_PRIVATE_KEY,
    VALID_PUBLIC_KEY
)

fun buildInvalidKeyResponse(messageSource: MessageSource) = ErrorResponse(
    listOf(
        Error(
            code = ErrorCode.INVALID_KEY, message = messageSource.getMessage(
                ErrorCode.INVALID_KEY, null, Locale.getDefault()
            )
        )
    )
)

fun buildInvalidAmountResponse(messageSource: MessageSource) = ErrorResponse(
    listOf(
        Error(
            code = ErrorCode.INVALID_AMOUNT, message = messageSource.getMessage(
                ErrorCode.INVALID_AMOUNT, null, Locale.getDefault()
            )
        )
    )
)

fun buildInvalidRequestHeaderResponse(messageSource: MessageSource) = ErrorResponse(
    listOf(
        Error(
            code = ErrorCode.INVALID_REQUEST_HEADER, message = messageSource.getMessage(
                ErrorCode.INVALID_REQUEST_HEADER, null, Locale.getDefault()
            )
        )
    )
)

fun buildValidTransactionResponse(amount: Long) = Transaction(
    amount,
    VALID_PUBLIC_KEY,
    VALID_PUBLIC_KEY
)

fun buildValidBlockchainResponse() = mutableListOf(
    BlockResponse(
        "1",
        null,
        Transaction(
            1,
            VALID_PUBLIC_KEY,
            VALID_PUBLIC_KEY
        ),
        "${Calendar.getInstance().timeInMillis}"
    )
)

object Commons {
    const val VALID_PRIVATE_KEY =
        "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDfEVpVO+G4LyM+0BDHZZk03pjVwK2Ee5FP57QOqaKe0Q6AwyUyR82rdms6710zFoPv1fb1DfVt+gSb1WN/tsApm5gH4X1m2O6PRnz2k7xBvJ3DUxxOelShWWnYTqvfae6nvA6EiJJuqx4knyxZ2/KccvAxeb0GKzSxfU4BMJVzC0pvLEZuFc+v+9ci5Kkqa53/L1piMIPTFK2ytUrEDFuVfrSYD2XJkt/B+lOaB4rNeG5ua/nIHCfEFyb2YS9mJQfKfbaoCHD0jFV7kD7pgo2/DCyRGTBNUYVdRLKHqL+unbHbrjvFFk8Th213xaYvb4N2h+1SFearOoSAczPXV5BHAgMBAAECggEACtuXYAq4I/T3bDlId8w0NuSW3+CvEYHKQu9uTkBnj/9xC0d0agwgsLXk5kt8Z94ysdviV29EgQPocYVjgHXuyJewQMHzDqyEgCjYMFBkm3aI31cOS6ucrvXhNwwtRYSsfzGiK0VSi1x87R9qUz1ng8H8ewwH7WLw/6qvv1MUEzNkOfi8Z3M+hAGE5N5D7gcQdmDjCnXp6ysYVvgfTNAD8yPIsAvDyYbOeggOmNST1vepfWf8PKagjkL6wpWETHy5y8Snpi1IWGqJncer01BFfbiebN79U5SovVUYR/BpjF2H4H1xAC1cZojdyji6ecKUctNWcqld4v/wMngiN+q7sQKBgQDyguUCH0yjc/2xtj3r97ggomazO+cF5+AWjqv8qiyxVQ4RpPyNhjyhE5DZF3vnW1TO2Oy+nplOGT6eWnDbNI/0GaeUabLKo+8/Ts+4Tb0ZcddokI7gosAnAaiKB6evVDbRHuwJD/3zbYNSWA0gK62usbI+gcBh11jWXusP18jRCwKBgQDreZpgYW6nFAZriFM21bDh4ZIhBcoF7/BqrTplnqnV+dc1+4s7/crEyxlWfIM9FRaWB1WwmSagIvA0s6u5TzFh+dQYEK1m8OJCm7c/lneL5VpPre3OarB67YTqn7pS5hy06zLAI8/s6SoeaDnU8Tb7BBbnoaSaJRwBuDdaZ3x7NQKBgBuX+zjHmO/MReSPqPINiLzl3autODc60AuhBj7Emjs/Q0+Mp1PvHzzwvf2Fp4qGoNq8Ygu6fhUd2DsqbUbz7yFGPgQsFrTp/FIGxKni5o5bnrf+ItO6kQSfMq8p+dGAUpBu5UqOsEWfg16+OBgeADAtdeuY1ewMFcamnDZ8RhRLAoGAZTySsn0xJYPmfrTNkfWtZm+kc64ZfRP7OOHhCW99dRFFBWBwLAqg8pqe+h/uDITUgQwGOzac9JwQyZe1bKYvhP/zv6Tr6CYIILrxvdTi8LJacYdFhkk4NqGHU5f4c0uba+zylMjQy/oysX/93R8XlDPd5RgJvHvX3qVkpul3C0ECgYBxWSQtv3sBnpjwHb8Hp7MB5kx/k460qhqsXZ0pHqSDopVhaYFi5qNrngFB9ESLzdKGH+Vpy9Z7U4sqGOym8Rz7gqfKOChakDqeFyjdncuu/tJSC+9uHIkrAXsBdepQ83A9GD3ohm7tt91tvKtU2aYId6eHi9RMw4VuSkMNzs/D7w=="
    const val VALID_PUBLIC_KEY =
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3xFaVTvhuC8jPtAQx2WZNN6Y1cCthHuRT+e0DqmintEOgMMlMkfNq3ZrOu9dMxaD79X29Q31bfoEm9Vjf7bAKZuYB+F9Ztjuj0Z89pO8Qbydw1McTnpUoVlp2E6r32nup7wOhIiSbqseJJ8sWdvynHLwMXm9Bis0sX1OATCVcwtKbyxGbhXPr/vXIuSpKmud/y9aYjCD0xStsrVKxAxblX60mA9lyZLfwfpTmgeKzXhubmv5yBwnxBcm9mEvZiUHyn22qAhw9IxVe5A+6YKNvwwskRkwTVGFXUSyh6i/rp2x2647xRZPE4dtd8WmL2+DdoftUhXmqzqEgHMz11eQRwIDAQAB"
    const val INVALID_PRIVATE_KEY =
        "MIIEvAIBADANBgkqhkiG8w0BAQEFAASCBKYwggSiAgEAAoIBAQDfEVpVO+G4LyM+0BDHZZk03pjVwK2Ee5FP57QOqaKe0Q6AwyUyR82rdms6710zFoPv1fb1DfVt+gSb1WN/tsApm5gH4X1m2O6PRnz2k7xBvJ3DUxxOelShWWnYTqvfae6nvA6EiJJuqx4knyxZ2/KccvAxeb0GKzSxfU4BMJVzC0pvLEZuFc+v+9ci5Kkqa53/L1piMIPTFK2ytUrEDFuVfrSYD2XJkt/B+lOaB4rNeG5ua/nIHCfEFyb2YS9mJQfKfbaoCHD0jFV7kD7pgo2/DCyRGTBNUYVdRLKHqL+unbHbrjvFFk8Th213xaYvb4N2h+1SFearOoSAczPXV5BHAgMBAAECggEACtuXYAq4I/T3bDlId8w0NuSW3+CvEYHKQu9uTkBnj/9xC0d0agwgsLXk5kt8Z94ysdviV29EgQPocYVjgHXuyJewQMHzDqyEgCjYMFBkm3aI31cOS6ucrvXhNwwtRYSsfzGiK0VSi1x87R9qUz1ng8H8ewwH7WLw/6qvv1MUEzNkOfi8Z3M+hAGE5N5D7gcQdmDjCnXp6ysYVvgfTNAD8yPIsAvDyYbOeggOmNST1vepfWf8PKagjkL6wpWETHy5y8Snpi1IWGqJncer01BFfbiebN79U5SovVUYR/BpjF2H4H1xAC1cZojdyji6ecKUctNWcqld4v/wMngiN+q7sQKBgQDyguUCH0yjc/2xtj3r97ggomazO+cF5+AWjqv8qiyxVQ4RpPyNhjyhE5DZF3vnW1TO2Oy+nplOGT6eWnDbNI/0GaeUabLKo+8/Ts+4Tb0ZcddokI7gosAnAaiKB6evVDbRHuwJD/3zbYNSWA0gK62usbI+gcBh11jWXusP18jRCwKBgQDreZpgYW6nFAZriFM21bDh4ZIhBcoF7/BqrTplnqnV+dc1+4s7/crEyxlWfIM9FRaWB1WwmSagIvA0s6u5TzFh+dQYEK1m8OJCm7c/lneL5VpPre3OarB67YTqn7pS5hy06zLAI8/s6SoeaDnU8Tb7BBbnoaSaJRwBuDdaZ3x7NQKBgBuX+zjHmO/MReSPqPINiLzl3autODc60AuhBj7Emjs/Q0+Mp1PvHzzwvf2Fp4qGoNq8Ygu6fhUd2DsqbUbz7yFGPgQsFrTp/FIGxKni5o5bnrf+ItO6kQSfMq8p+dGAUpBu5UqOsEWfg16+OBgeADAtdeuY1ewMFcamnDZ8RhRLAoGAZTySsn0xJYPmfrTNkfWtZm+kc64ZfRP7OOHhCW99dRFFBWBwLAqg8pqe+h/uDITUgQwGOzac9JwQyZe1bKYvhP/zv6Tr6CYIILrxvdTi8LJacYdFhkk4NqGHU5f4c0uba+zylMjQy/oysX/93R8XlDPd5RgJvHvX3qVkpul3C0ECgYBxWSQtv3sBnpjwHb8Hp7MB5kx/k460qhqsXZ0pHqSDopVhaYFi5qNrngFB9ESLzdKGH+Vpy9Z7U4sqGOym8Rz7gqfKOChakDqeFyjdncuu/tJSC+9uHIkrAXsBdepQ83A9GD3ohm7tt91tvKtU2aYId6eHi9RMw4VuSkMNzs/D7w=="
    const val INVALID_PUBLIC_KEY =
        "MIIBIjANBgkqhkiG8w0BAQEFAAOCAQ8AMIIBCgKCAQEA3xFaVTvhuC8jPtAQx2WZNN6Y1cCthHuRT+e0DqmintEOgMMlMkfNq3ZrOu9dMxaD79X29Q31bfoEm9Vjf7bAKZuYB+F9Ztjuj0Z89pO8Qbydw1McTnpUoVlp2E6r32nup7wOhIiSbqseJJ8sWdvynHLwMXm9Bis0sX1OATCVcwtKbyxGbhXPr/vXIuSpKmud/y9aYjCD0xStsrVKxAxblX60mA9lyZLfwfpTmgeKzXhubmv5yBwnxBcm9mEvZiUHyn22qAhw9IxVe5A+6YKNvwwskRkwTVGFXUSyh6i/rp2x2647xRZPE4dtd8WmL2+DdoftUhXmqzqEgHMz11eQRwIDAQAB"
    const val INITIAL_BALANCE = 0L
}
