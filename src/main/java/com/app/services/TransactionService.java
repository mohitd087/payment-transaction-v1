package com.app.services;

import com.app.entities.Transaction;
import com.app.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getOperationType().getDescription().equals("Withdrawal") 
            || transaction.getOperationType().getDescription().equals("Normal Purchase")) {
            transaction.setAmount(transaction.getAmount().negate());
        }
        return transactionRepository.save(transaction);
    }
}
