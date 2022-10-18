package com.testing.piggybank.transaction;

import com.testing.piggybank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions(final Integer limit) {
        return transactionRepository.getTransactions(limit).stream().sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime())).toList();
    }
}
