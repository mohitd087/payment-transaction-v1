package com.app.services;

import com.app.dto.TransactionRequest;
import com.app.entities.Account;
import com.app.entities.OperationType;
import com.app.entities.Transaction;
import com.app.exceptions.AccountNotFoundException;
import com.app.exceptions.OperationTypeNotFoundException;
import com.app.repositories.AccountRepository;
import com.app.repositories.OperationTypeRepository;
import com.app.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @Transactional
    public Transaction createTransaction(TransactionRequest transactionRequest) {
        Account account = accountRepository.findById(transactionRequest.getAccountId())
            .orElseThrow(() -> new AccountNotFoundException("Account with ID " + transactionRequest.getAccountId() + " not found"));
        
        OperationType operationType = operationTypeRepository.findById(transactionRequest.getOperationTypeId())
            .orElseThrow(() -> new OperationTypeNotFoundException("Operation Type with ID " + transactionRequest.getOperationTypeId() + " not found"));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(
            operationType.getDescription().equals("Credit Voucher") 
            ? transactionRequest.getAmount() 
            : transactionRequest.getAmount().negate()
        );
        return transactionRepository.save(transaction);
    }
}
