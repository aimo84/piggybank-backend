package com.testing.piggybank.transaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/api/v1/transactions")
public class TransactionController {
	private final TransactionService transactionService;
	
	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@GetMapping
	public ResponseEntity<GetTransactionsResponse> getTransactions() {
		GetTransactionsResponse response = new GetTransactionsResponse();
		response.setTransactions(transactionService.getTransactions());
		return ResponseEntity.ok(response);
	}

}
