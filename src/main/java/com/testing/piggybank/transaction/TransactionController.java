package com.testing.piggybank.transaction;

import com.testing.piggybank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<GetTransactionsResponse> getTransactions(
            @PathVariable final long accountId,
            @RequestParam(name = "limit", required = false) final Integer limit) {

        final List<Transaction> transactions = transactionService.getTransactions(limit, accountId);
        final List<TransactionResponse> transactionsResponse = transactions
                .stream()
                .map(transaction -> mapTransactionToTransactionResponse(transaction, accountId))
                .toList();

        final GetTransactionsResponse response = new GetTransactionsResponse();
        response.setTransactions(transactionsResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody final CreateTransactionRequest request) {
        transactionService.createTransaction(mapRequestToTransaction(request));
        return ResponseEntity.ok().build();
    }

    private TransactionResponse mapTransactionToTransactionResponse(final Transaction transaction, final long accountId) {
        final TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId());

        transactionResponse.setDescription(transaction.getDescription());
        transactionResponse.setDateTime(transaction.getDateTime());
        transactionResponse.setToAccountId(transaction.getToAccountId());
        transactionResponse.setFromAccountId(transaction.getFromAccountId());

        // When transaction is initiated from current account, the amount is credit.
        if (accountId == transaction.getFromAccountId()) {
            transactionResponse.setAmount(transaction.getAmount().negate());
        } else {
            transactionResponse.setAmount(transaction.getAmount());
        }
        return transactionResponse;
    }

    private Transaction mapRequestToTransaction(final CreateTransactionRequest request) {
        final Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setDescription(request.getDescription());
        transaction.setFromAccountId(request.getFromAccountId());
        transaction.setToAccountId(request.getToAccountId());
        transaction.setDateTime(Instant.now());
        return transaction;
    }
}
