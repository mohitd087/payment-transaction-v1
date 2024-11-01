package com.app.mappers;


import com.app.dto.AccountResponse;
import com.app.dto.TransactionResponse;
import com.app.entities.Account;
import com.app.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public AccountResponse toAccountResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setAccountId(account.getAccountId());
        response.setDocumentNumber(account.getDocumentNumber());
        return response;
    }

    public TransactionResponse toTransactionResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(transaction.getTransactionId());
        response.setAccountId(transaction.getAccount().getAccountId());
        response.setOperationTypeId(transaction.getOperationType().getOperationTypeId());
        response.setAmount(transaction.getAmount());
        response.setEventDate(transaction.getEventDate());
        return response;
    }
}
