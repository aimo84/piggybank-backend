package com.testing.piggybank.transaction;

import com.testing.piggybank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        final List<TransactionResponse> transactionResponses = transactionService.getTransactions(limit, accountId)
                .stream()
                .map(transaction -> mapTransactionToTransactionResponse(transaction, accountId))
                .collect(Collectors.toList());
        response.setTransactions(transactionResponses);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody final CreateTransactionRequest request) {
        transactionService.createTransaction(request);
        return ResponseEntity.ok().build();
    }

    public TransactionResponse mapTransactionToTransactionResponse(final Transaction transaction, final long accountId) {
        final TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId());

        transactionResponse.setDescription(transaction.getDescription());
        transactionResponse.setDateTime(transaction.getDateTime());

        // When transaction is initiated from current account, the amount is credit.
        if (accountId == transaction.getSenderAccount().getId()) {
            transactionResponse.setAmount(transaction.getAmount().negate());
        } else {
            transactionResponse.setAmount(transaction.getAmount());
        }
        return transactionResponse;
    }

}
