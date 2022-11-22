package com.testing.piggybank.transaction;

import com.testing.piggybank.model.Transaction;

import java.util.List;

public class GetTransactionsResponse {
    List<TransactionResponse> transactions;

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponse> transactions) {
        this.transactions = transactions;
    }
}
