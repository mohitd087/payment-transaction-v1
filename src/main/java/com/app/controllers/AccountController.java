package com.app.controllers;


import com.app.dto.AccountRequest;
import com.app.dto.AccountResponse;
import com.app.entities.Account;
import com.app.mappers.EntityMapper;
import com.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private EntityMapper mapper;

    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        Account account = accountService.createAccount(accountRequest);
        return mapper.toAccountResponse(account);
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccount(accountId);
        return mapper.toAccountResponse(account);
    }
}
