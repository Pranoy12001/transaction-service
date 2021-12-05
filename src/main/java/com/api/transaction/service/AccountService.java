package com.api.transaction.service;

import com.api.transaction.model.TransactionPayload;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Account Service interface
 */
public interface AccountService {
    public ResponseEntity<Map> updateAccount(TransactionPayload payload);
}
