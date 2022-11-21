package com.testing.piggybank.transaction;

import com.testing.piggybank.account.AccountService;
import com.testing.piggybank.model.Direction;
import com.testing.piggybank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CurrencyConverterService converterService;
    private final AccountService accountService;

    @Autowired
    public TransactionService(final TransactionRepository transactionRepository,
                              final CurrencyConverterService converterService,
                              final AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.converterService = converterService;
        this.accountService = accountService;
    }

    public List<Transaction> getTransactions(final Integer limit, final long accountId) {
        return transactionRepository.getTransactions(limit, accountId)
                .stream()
                .sorted(TransactionService::sortDescByDateTime)
                .toList();
    }

    public void createTransaction(final Transaction transaction) {
        // Convert the currency to euro.
        final BigDecimal amountInEuro = converterService.toEuro(transaction.getCurrency(), transaction.getAmount());
        transaction.setAmount(amountInEuro);

        // Determine the latest id. Normally done by db.
        final long nextId = transactionRepository.getNextId();
        transaction.setId(nextId);

        // Update balances
        long fromAccountId = transaction.getSenderAccount().getId();
        accountService.updateBalance(fromAccountId, transaction.getAmount(), Direction.CREDIT);

        long toAccountId = transaction.getReceiverAccount().getId();
        accountService.updateBalance(toAccountId, transaction.getAmount(), Direction.DEBIT);

        // Save the transaction.
        transactionRepository.save(transaction);
    }

    private static int sortDescByDateTime(final Transaction t1, final Transaction t2) {
        return t2.getDateTime().compareTo(t1.getDateTime());
    }
}
