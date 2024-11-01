package com.app.controllers;

import com.app.dto.AccountRequest;
import com.app.dto.AccountResponse;
import com.app.entities.Account;
import com.app.mappers.EntityMapper;
import com.app.services.AccountService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

//@WebMvcTest(AccountController.class)
//@SpringBootTest
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private EntityMapper mapper;

    @InjectMocks
    private AccountController accountController;

    @Autowired
    private ObjectMapper objectMapper;

    public AccountControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void createAccount_shouldReturnAccountResponse() throws Exception {
        // Arrange
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setDocumentNumber("12345678900");

        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678900");

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(1L);
        accountResponse.setDocumentNumber("12345678900");

        when(accountService.createAccount(any(AccountRequest.class))).thenReturn(account);
        when(mapper.toAccountResponse(account)).thenReturn(accountResponse);

        // Act & Assert
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_id").value(1L))
                .andExpect(jsonPath("$.document_number").value("12345678900"));
    }

    @Test
    public void getAccount_shouldReturnAccountResponse() throws Exception {
        // Arrange
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678900");

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(1L);
        accountResponse.setDocumentNumber("12345678900");

        when(accountService.getAccount(1L)).thenReturn(account);
        when(mapper.toAccountResponse(account)).thenReturn(accountResponse);

        // Act & Assert
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_id").value(1L))
                .andExpect(jsonPath("$.document_number").value("12345678900"));
    }
}
