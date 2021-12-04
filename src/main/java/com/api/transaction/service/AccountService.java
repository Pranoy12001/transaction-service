package com.api.transaction.service;

import com.api.transaction.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AccountService {
    public ResponseEntity<Map> updateAccount(Transaction transaction);
}
