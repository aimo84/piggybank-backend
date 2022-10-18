package com.testing.piggybank.model;

import java.util.Collections;
import java.util.List;

public class User {
    private String name;
    private List<Account> accounts = Collections.emptyList();
    private List<Transaction> transactions = Collections.emptyList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
