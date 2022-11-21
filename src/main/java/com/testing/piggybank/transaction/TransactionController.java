package com.testing.piggybank.transaction;

import com.testing.piggybank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping(path = "/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<GetTransactionsResponse> getTransactions(
            @RequestParam(name = "limit", required = false) final Integer limit,
            @PathVariable final long accountId) {

        final GetTransactionsResponse response = new GetTransactionsResponse();
        response.setTransactions(transactionService.getTransactions(limit, accountId));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody final CreateTransactionRequest request) {
        transactionService.createTransaction(mapRequestToTransaction(request));
        return ResponseEntity.ok().build();
    }

    private Transaction mapRequestToTransaction(final CreateTransactionRequest request) {
        final Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setDescription(request.getDescription());
//        transaction.setFromAccountId(request.getFromAccountId());
//        transaction.setToAccountId(request.getToAccountId());
        transaction.setDateTime(Instant.now());
        return transaction;
    }
}
