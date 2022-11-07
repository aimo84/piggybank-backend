package com.testing.piggybank.account;

import com.testing.piggybank.model.Account;
import com.testing.piggybank.model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    public AccountRepository() {
        var account1 = new Account(new BigDecimal("1000.00"),"Spaarrekening van Mel", 1L, 1L);
        var account2 = new Account(new BigDecimal("5000.50"),"Toekomst rekening van Mel", 2L, 1L);
        var account3 = new Account(new BigDecimal("123.45"),"Betaalrekening", 3L, 2L);
        var account4 = new Account(new BigDecimal("22457.11"),"Betaalrekening", 4L, 3L);
        var account5 = new Account(new BigDecimal("0.00"),"Spaarrekening", 5L, 4L);

        this.accounts.add(account1);
        this.accounts.add(account2);
        this.accounts.add(account3);
        this.accounts.add(account4);
        this.accounts.add(account5);
    }

    public Optional<Account> getAccount(long accountId) {
        return accounts.stream().filter(account -> account.getId() == accountId).findFirst();
    }
}
