package com.testing.piggybank.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<GetTransactionsResponse> getTransactions(@RequestParam(name = "limit", required = false) Integer limit) {
        GetTransactionsResponse response = new GetTransactionsResponse();
        response.setTransactions(transactionService.getTransactions(limit));
        return ResponseEntity.ok(response);
    }

}
