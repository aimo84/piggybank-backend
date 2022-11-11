package com.testing.piggybank.account;

import com.testing.piggybank.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {
    private final static String HEADER_USER_ID = "X-User-Id";
    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable final long accountId) {
        return accountService
                .getAccount(accountId)
                .map(account -> {
                    final AccountResponse accountResponse = mapAccountToAccountResponse(account);
                    return ResponseEntity.ok(accountResponse);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<GetAccountsResponse> getAccounts(@RequestHeader(HEADER_USER_ID) final long userId) {
        final List<Account> accounts = accountService.getAccounts(userId);

        final GetAccountsResponse accountsResponse = new GetAccountsResponse();
        accountsResponse.setAccounts(accounts.stream()
                .map(this::mapAccountToAccountResponse)
                .collect(Collectors.toList())
        );

        return ResponseEntity.ok(accountsResponse);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateAccount(@RequestBody @Valid UpdateAccountRequest request) {
        // TODO: Implement.
        System.out.println("Update account name: " + request.getAccountName() + " for accountid: " + request.getAccountId());

        return ResponseEntity.ok().build();
    }

    private AccountResponse mapAccountToAccountResponse(final Account account) {
        final AccountResponse response = new AccountResponse();
        response.setName(account.getName());
        response.setBalance(account.getBalance());
        response.setId(account.getId());
        return response;
    }
}
