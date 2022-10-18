package com.testing.piggybank.transaction;

import com.testing.piggybank.model.Transaction;

import java.util.List;

public class GetTransactionsResponse {
    List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
