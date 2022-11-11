package com.testing.piggybank.model;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;
    private String name;
    private final long id;
    private final long userId;

    public Account(BigDecimal balance, String name, long id, long userId) {
        this.balance = balance;
        this.name = name;
        this.id = id;
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }
}
