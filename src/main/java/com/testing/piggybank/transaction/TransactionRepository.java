package com.testing.piggybank.transaction;

import com.testing.piggybank.model.Currency;
import com.testing.piggybank.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Repository filled with mock data.
 */
@Service
public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository() {
        // Lunch
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(new BigDecimal("15.00"));
        transaction.setCurrency(Currency.EURO);
        transaction.setDescription("Lunch voor je verjaardag");
        transaction.setFromAccountId(1L);
        transaction.setToAccountId(2L);
        transaction.setDateTime(Instant.parse("2022-10-05T13:15:00.00Z"));

        // Taxi
        Transaction transaction1 = new Transaction();
        transaction1.setId(2L);
        transaction1.setAmount(new BigDecimal("31.35"));
        transaction1.setCurrency(Currency.EURO);
        transaction1.setDescription("Taxi ritje Nijmegen");
        transaction1.setFromAccountId(1L);
        transaction1.setToAccountId(4L);
        transaction1.setDateTime(Instant.parse("2022-10-05T13:30:00.00Z"));

        // Hotel
        Transaction transaction2 = new Transaction();
        transaction2.setId(3L);
        transaction2.setAmount(new BigDecimal("-137.29"));
        transaction2.setCurrency(Currency.EURO);
        transaction2.setDescription("Hotel overnachting + ontbijt");
        transaction2.setFromAccountId(1L);
        transaction2.setToAccountId(5L);
        transaction2.setDateTime(Instant.parse("2022-10-07T13:30:00.00Z"));

        // Lunch
        Transaction transaction3 = new Transaction();
        transaction3.setId(4L);
        transaction3.setAmount(new BigDecimal("15.00"));
        transaction3.setCurrency(Currency.EURO);
        transaction3.setDescription("Te weinig lunchgeld betaald");
        transaction3.setFromAccountId(1L);
        transaction3.setToAccountId(2L);
        transaction3.setDateTime(Instant.parse("2022-10-08T13:30:00.00Z"));

        transactions.add(transaction);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
    }

    public List<Transaction> getTransactions(final Integer limit) {
        if(limit != null) {
            return transactions.subList(transactions.size() - limit, transactions.size());
        }
        return transactions;
    }

    public List<Transaction> getTransactions() {
        return getTransactions(null);
    }

    public void save(final Transaction transaction) {
        transactions.add(transaction);
    }

    public long getNextId() {
        long lastId = getTransactions()
                .stream()
                .max(Comparator.comparingLong(Transaction::getId))
                .map(Transaction::getId)
                .orElse(0L);
        return lastId + 1L;
    }
}
