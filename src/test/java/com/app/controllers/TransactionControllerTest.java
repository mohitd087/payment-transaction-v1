package com.app.controllers;

import com.app.dto.TransactionRequest;
import com.app.dto.TransactionResponse;
import com.app.entities.Transaction;
import com.app.mappers.EntityMapper;
import com.app.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private EntityMapper mapper;

    @InjectMocks
    private TransactionController transactionController;

    @Autowired
    private ObjectMapper objectMapper;

    public TransactionControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void createTransaction_shouldReturnTransactionResponse() throws Exception {
        // Arrange
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(1L);
        transactionRequest.setOperationTypeId(4L);
        transactionRequest.setAmount(BigDecimal.valueOf(100.00));

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setAmount(BigDecimal.valueOf(100.00));
        transaction.setEventDate(LocalDateTime.now());

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionId(1L);
        transactionResponse.setAccountId(1L);
        transactionResponse.setOperationTypeId(4L);
        transactionResponse.setAmount(BigDecimal.valueOf(100.00));
        transactionResponse.setEventDate(transaction.getEventDate());

        when(transactionService.createTransaction(any(TransactionRequest.class))).thenReturn(transaction);
        when(mapper.toTransactionResponse(transaction)).thenReturn(transactionResponse);

        // Act & Assert
        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transaction_id").value(1L))
                .andExpect(jsonPath("$.account_id").value(1L))
                .andExpect(jsonPath("$.operation_type_id").value(4L))
                .andExpect(jsonPath("$.amount").value(100.00));
    }
}
