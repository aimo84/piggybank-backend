package com.testing.piggybank.transaction;

import com.testing.piggybank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CurrencyConverterService converterService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CurrencyConverterService converterService) {
        this.transactionRepository = transactionRepository;
        this.converterService = converterService;
    }

    public List<Transaction> getTransactions(final Integer limit) {
        return transactionRepository.getTransactions(limit)
                .stream()
                .sorted(TransactionService::compareDateTime)
                .toList();
    }

    public void createTransaction(final Transaction transaction) {
        // Convert the currency to euro.
        transaction.setAmount(converterService.toEuro(transaction.getCurrency(), transaction.getAmount()));

        transactionRepository.save(transaction);
    }

    private static int compareDateTime(final Transaction t1, final Transaction t2) {
        return t2.getDateTime().compareTo(t1.getDateTime());
    }
}
