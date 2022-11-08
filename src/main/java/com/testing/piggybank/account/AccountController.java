package com.testing.piggybank.account;

import com.testing.piggybank.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable long accountId) {
        return accountService
                .getAccount(accountId)
                .map(account -> {
                    final AccountResponse accountResponse = mapAccountToAccountResponse(account);
                    return ResponseEntity.ok(accountResponse);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<GetAccountsResponse> getAccounts() {
        final long userId = 1L; // TODO: Get userId from token or session.

        final List<Account> accounts = accountService.getAccounts(userId);

        GetAccountsResponse accountsResponse = new GetAccountsResponse();
        accountsResponse.setAccounts(accounts.stream()
                .map(this::mapAccountToAccountResponse)
                .collect(Collectors.toList())
        );
        return ResponseEntity.ok(accountsResponse);
    }

    private AccountResponse mapAccountToAccountResponse(final Account account) {
        final AccountResponse response = new AccountResponse();
        response.setName(account.getName());
        response.setBalance(account.getBalance());
        response.setId(account.getId());
        return response;
    }
}
