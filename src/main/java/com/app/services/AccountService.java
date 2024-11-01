package com.app.services;

import com.app.dto.AccountRequest;
import com.app.entities.Account;
import com.app.exceptions.AccountNotFoundException;
import com.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(AccountRequest accountRequest) {

        Account account = new Account();
        account.setDocumentNumber(accountRequest.getDocumentNumber());
        return accountRepository.save(account);
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException("Account with ID " + accountId + " not found"));
    }
}
