package com.testing.piggybank.account;

import com.testing.piggybank.model.Account;
import com.testing.piggybank.model.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    public AccountRepository() {
        var account1 = new Account(new BigDecimal("1001.15"),"Rekening van Melvin", 1L, 1L);
        var account2 = new Account(new BigDecimal("123.45"),"Rekening van Bauke", 3L, 2L);
        var account3 = new Account(new BigDecimal("22457.11"),"Rekening van Cem", 4L, 3L);
        var account4 = new Account(new BigDecimal("0.00"),"Rekening van Sophie", 5L, 4L);

        this.accounts.add(account1);
        this.accounts.add(account2);
        this.accounts.add(account3);
        this.accounts.add(account4);
    }

    public Optional<Account> getAccount(long accountId) {
        return accounts.stream()
                .filter(account -> account.getId() == accountId)
                .findFirst();
    }

    public List<Account> getUserAccounts(long userId) {
        return accounts.stream()
                .filter(account -> account.getUserId() == userId)
                .collect(Collectors.toList());
    }
}
