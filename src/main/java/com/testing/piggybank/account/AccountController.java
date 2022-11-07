package com.testing.piggybank.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {
    @GetMapping(path = "{accountId}")
    public ResponseEntity<HttpStatus>    getAccount(@PathVariable long accountId) {
        System.out.println(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
