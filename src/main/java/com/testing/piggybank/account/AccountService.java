package com.testing.piggybank.account;

import com.testing.piggybank.model.Account;
import com.testing.piggybank.model.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccount(final long accountId) {
        return accountRepository.findById(accountId);
    }

    public List<Account> getAccounts(long userId) {

        return Collections.emptyList();
//        return accountRepository.getUserAccounts(userId);
    }

    public void updateBalance(final long accountId, final BigDecimal amount, final Direction direction) {
        final Account accountToUpdate = getAccount(accountId).orElseThrow(RuntimeException::new);
        final BigDecimal amountToUpdate = direction.equals(Direction.CREDIT) ? amount.negate() : amount;

        final BigDecimal currentBalance = accountToUpdate.getBalance();
        final BigDecimal newBalance = currentBalance.add(amountToUpdate);

        accountToUpdate.setBalance(newBalance);
    }
}
