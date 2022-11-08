package com.testing.piggybank.account;

import com.testing.piggybank.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return accountRepository.getAccount(accountId);
    }

    public List<Account> getAccounts(long userId) {
        return accountRepository.getUserAccounts(userId);
    }
}
