package com.api.transaction.controller;

import com.api.transaction.exception.ClientException;
import com.api.transaction.model.Transaction;
import com.api.transaction.service.AccountService;
import com.api.transaction.validator.RequestValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {
    private AccountService accountService;
    ObjectMapper mapper;

    public AccountController(AccountService accountService, ObjectMapper mapper) {
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @GetMapping("/test-application")
    public ResponseEntity<String> testApplication() {
        return new ResponseEntity<>("Application is running...", HttpStatus.OK);
    }

    @PostMapping("/transfer-money")
    public ResponseEntity<Map> triggerTransaction(@RequestBody String body) {
        Transaction transaction;
        try {
            transaction = mapper.readValue(body, Transaction.class);
        } catch (JsonProcessingException ex) {
            return getErrorResponseEntity(ex);
        }

        try {
            RequestValidator.validate(transaction);
        } catch (ClientException ex) {
            return getErrorResponseEntity(ex);
        }
        return accountService.updateAccount(transaction);
    }

    private ResponseEntity<Map> getErrorResponseEntity(Exception ex) {
        ClientException clientException = ClientException.create("400", "invalid request", ex);
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("code", clientException.getCode());
        errorMap.put("message", clientException.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
