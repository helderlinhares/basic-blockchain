package me.hl.blockchain.rest

import com.google.gson.Gson
import me.hl.blockchain.Commons.INITIAL_BALANCE
import me.hl.blockchain.Commons.INVALID_PRIVATE_KEY
import me.hl.blockchain.Commons.INVALID_PUBLIC_KEY
import me.hl.blockchain.Commons.VALID_PRIVATE_KEY
import me.hl.blockchain.Commons.VALID_PUBLIC_KEY
import me.hl.blockchain.buildInvalidAmountResponse
import me.hl.blockchain.buildInvalidKeyResponse
import me.hl.blockchain.buildInvalidRequestHeaderResponse
import me.hl.blockchain.buildValidBlockchainResponse
import me.hl.blockchain.buildValidTransactionResponse
import me.hl.blockchain.buildValidWalletResponse
import me.hl.blockchain.shared.ErrorCode
import me.hl.blockchain.shared.InvalidAmountException
import me.hl.blockchain.shared.InvalidKeyException
import me.hl.blockchain.domain.chain.ChainService
import me.hl.blockchain.domain.transaction.TransactionService
import me.hl.blockchain.domain.wallet.WalletService
import me.hl.blockchain.shared.KeyConverter.toPrivateKey
import me.hl.blockchain.shared.KeyConverter.toPublicKey
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.MessageSource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class BlockchainControllerTest {
    @MockBean
    private lateinit var chainService: ChainService

    @MockBean
    private lateinit var walletService: WalletService

    @MockBean
    private lateinit var transactionService: TransactionService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var messageSource: MessageSource

    @Test
    fun `Should return Created When creating a wallet`() {
        val validWalletResponse = buildValidWalletResponse(INITIAL_BALANCE)
        BDDMockito.given(walletService.createWallet()).willReturn(validWalletResponse)

        mockMvc.get("/blockchain/wallet")
            .andDo { print() }
            .andExpect { status { isCreated() } }
            .andExpect {
                content {
                    json(Gson().toJson(validWalletResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return Success When opening with a valid private key`() {
        val validWalletResponse = buildValidWalletResponse(INITIAL_BALANCE)
        BDDMockito.given(walletService.openWallet(VALID_PRIVATE_KEY)).willReturn(validWalletResponse)

        mockMvc.get("/blockchain/wallet/open") {
            header("private-key", VALID_PRIVATE_KEY)
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect {
                content {
                    json(Gson().toJson(validWalletResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When opening with an invalid private key`() {
        val invalidKeyResponse = buildInvalidKeyResponse(messageSource)
        BDDMockito.given(walletService.openWallet(INVALID_PRIVATE_KEY))
            .willThrow(InvalidKeyException(ErrorCode.INVALID_KEY))

        mockMvc.get("/blockchain/wallet/open") {
            header("private-key", INVALID_PRIVATE_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidKeyResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When opening without required header`() {
        val invalidRequestHeaderResponse = buildInvalidRequestHeaderResponse(messageSource)

        mockMvc.get("/blockchain/wallet/open")
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidRequestHeaderResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return Success When mining with a valid private key`() {
        val miningAmount = 1L
        val validTransactionResponse = buildValidTransactionResponse(miningAmount)
        BDDMockito.given(transactionService.mineBlock(miningAmount, VALID_PRIVATE_KEY.toPrivateKey()))
            .willReturn(validTransactionResponse)

        mockMvc.post("/blockchain/wallet/mine/$miningAmount") {
            header("private-key", VALID_PRIVATE_KEY)
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect {
                content {
                    json(Gson().toJson(validTransactionResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When mining with an invalid private key`() {
        val miningAmount = 1L
        val invalidKeyResponse = buildInvalidKeyResponse(messageSource)

        mockMvc.post("/blockchain/wallet/mine/$miningAmount") {
            header("private-key", INVALID_PRIVATE_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidKeyResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When mining with an negative amount`() {
        val miningAmount = -1L
        val invalidAmountResponse = buildInvalidAmountResponse(messageSource)
        BDDMockito.given(transactionService.mineBlock(miningAmount, VALID_PRIVATE_KEY.toPrivateKey()))
            .willThrow(InvalidAmountException(ErrorCode.INVALID_AMOUNT))

        mockMvc.post("/blockchain/wallet/mine/$miningAmount") {
            header("private-key", VALID_PRIVATE_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidAmountResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When mining with a zero amount`() {
        val miningAmount = 0L
        val invalidAmountResponse = buildInvalidAmountResponse(messageSource)
        BDDMockito.given(transactionService.mineBlock(miningAmount, VALID_PRIVATE_KEY.toPrivateKey()))
            .willThrow(InvalidAmountException(ErrorCode.INVALID_AMOUNT))

        mockMvc.post("/blockchain/wallet/mine/$miningAmount") {
            header("private-key", VALID_PRIVATE_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidAmountResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When mining without required header`() {
        val miningAmount = 1L
        val invalidRequestHeaderResponse = buildInvalidRequestHeaderResponse(messageSource)

        mockMvc.post("/blockchain/wallet/mine/$miningAmount")
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidRequestHeaderResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return Success When sending coin with valid private key and public key`() {
        val sendingAmount = 1L
        val validTransactionResponse = buildValidTransactionResponse(sendingAmount)
        BDDMockito.given(
            transactionService
                .sendCoin(sendingAmount, VALID_PRIVATE_KEY.toPrivateKey(), VALID_PUBLIC_KEY.toPublicKey())
        )
            .willReturn(validTransactionResponse)

        mockMvc.post("/blockchain/wallet/send/$sendingAmount") {
            header("sender-private-key", VALID_PRIVATE_KEY)
            header("receiver-public-key", VALID_PUBLIC_KEY)
        }
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect {
                content {
                    json(Gson().toJson(validTransactionResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When sending coin with an invalid private key`() {
        val sendingAmount = 1L
        val invalidKeyResponse = buildInvalidKeyResponse(messageSource)

        mockMvc.post("/blockchain/wallet/send/$sendingAmount") {
            header("sender-private-key", INVALID_PRIVATE_KEY)
            header("receiver-public-key", VALID_PUBLIC_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidKeyResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When sending coin with an invalid public key`() {
        val sendingAmount = 1L
        val invalidKeyResponse = buildInvalidKeyResponse(messageSource)

        mockMvc.post("/blockchain/wallet/send/$sendingAmount") {
            header("sender-private-key", VALID_PRIVATE_KEY)
            header("receiver-public-key", INVALID_PUBLIC_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidKeyResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When sending coin with an negative amount`() {
        val sendingAmount = -1L
        val invalidAmountResponse = buildInvalidAmountResponse(messageSource)
        BDDMockito.given(
            transactionService
                .sendCoin(sendingAmount, VALID_PRIVATE_KEY.toPrivateKey(), VALID_PUBLIC_KEY.toPublicKey())
        )
            .willThrow(InvalidAmountException(ErrorCode.INVALID_AMOUNT))

        mockMvc.post("/blockchain/wallet/send/$sendingAmount") {
            header("sender-private-key", VALID_PRIVATE_KEY)
            header("receiver-public-key", VALID_PUBLIC_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidAmountResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When sending coin with a zero amount`() {
        val sendingAmount = 0L
        val invalidAmountResponse = buildInvalidAmountResponse(messageSource)
        BDDMockito.given(
            transactionService
                .sendCoin(sendingAmount, VALID_PRIVATE_KEY.toPrivateKey(), VALID_PUBLIC_KEY.toPublicKey())
        )
            .willThrow(InvalidAmountException(ErrorCode.INVALID_AMOUNT))


        mockMvc.post("/blockchain/wallet/send/$sendingAmount") {
            header("sender-private-key", VALID_PRIVATE_KEY)
            header("receiver-public-key", VALID_PUBLIC_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidAmountResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return BadRequest When sending coin without required header`() {
        val sendingAmount = 1L
        val invalidRequestHeaderResponse = buildInvalidRequestHeaderResponse(messageSource)

        mockMvc.post("/blockchain/wallet/send/$sendingAmount")
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidRequestHeaderResponse).trimIndent())
                }
            }

        mockMvc.post("/blockchain/wallet/send/$sendingAmount") {
            header("sender-private-key", VALID_PRIVATE_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidRequestHeaderResponse).trimIndent())
                }
            }

        mockMvc.post("/blockchain/wallet/send/$sendingAmount") {
            header("receiver-public-key", VALID_PUBLIC_KEY)
        }
            .andDo { print() }
            .andExpect { status { isBadRequest() } }
            .andExpect {
                content {
                    json(Gson().toJson(invalidRequestHeaderResponse).trimIndent())
                }
            }
    }

    @Test
    fun `Should return Success When getting the blockchain`() {
        val validBlockchainResponse = buildValidBlockchainResponse()
        BDDMockito.given(chainService.getChainBlocks()).willReturn(validBlockchainResponse)

        mockMvc.get("/blockchain")
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect {
                content {
                    json(Gson().toJson(validBlockchainResponse).trimIndent())
                }
            }
    }

}