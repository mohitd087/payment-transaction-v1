package com.app.controllers;

import com.app.dto.TransactionRequest;
import com.app.dto.TransactionResponse;
import com.app.entities.Transaction;
import com.app.mappers.EntityMapper;
import com.app.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private EntityMapper mapper;

    @PostMapping
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.createTransaction(transactionRequest);
        return mapper.toTransactionResponse(transaction);
    }
}
