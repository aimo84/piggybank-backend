package com.testing.piggybank.transaction;

import java.util.List;

import com.testing.piggybank.model.Transaction;

public class GetTransactionsResponse {
	List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
