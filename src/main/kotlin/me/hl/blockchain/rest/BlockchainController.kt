package me.hl.blockchain.rest

import me.hl.blockchain.domain.block.Block
import me.hl.blockchain.domain.chain.ChainService
import me.hl.blockchain.domain.transaction.TransactionService
import me.hl.blockchain.domain.wallet.WalletService
import me.hl.blockchain.shared.KeyConverter.toPrivateKey
import me.hl.blockchain.shared.KeyConverter.toPublicKey
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.LinkedList

@RestController
@RequestMapping("/blockchain", produces = [MediaType.APPLICATION_JSON_VALUE])
class BlockchainController(
    private val walletService: WalletService,
    private val transactionService: TransactionService,
    private val chainService: ChainService
) {

    @GetMapping("/wallet", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun createWallet() = walletService.createWallet()

    @GetMapping("/wallet/open", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun openWallet(@RequestHeader("private-key") privateKey: String) =
        walletService.openWallet(privateKey)

    @PostMapping("/wallet/mine/{coins}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun mineCoin(
        @RequestHeader("private-key") privateKey: String,
        @PathVariable coins: Long
    ) = transactionService.mineBlock(coins, privateKey.toPrivateKey())

    @PostMapping("/wallet/send/{coins}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sendCoin(
        @RequestHeader("sender-private-key") privateKey: String,
        @RequestHeader("receiver-public-key") receiverPublicKey: String,
        @PathVariable coins: Long
    ) = transactionService.sendCoin(coins, privateKey.toPrivateKey(), receiverPublicKey.toPublicKey())

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listBlockChain() = chainService.getChainBlocks().toResponse()

    private fun LinkedList<Block>.toResponse(): ArrayList<BlockResponse?> {
        val li = this.listIterator()
        val blockChainResponse = ArrayList<BlockResponse?>(this.size)
        while (li.hasNext()) {
            val node = li.next()
            blockChainResponse.add(
                BlockResponse(li.previousIndex(), li.nextIndex(), node.transaction, node.time, node.hash())
            )
        }
        return blockChainResponse
    }
}
