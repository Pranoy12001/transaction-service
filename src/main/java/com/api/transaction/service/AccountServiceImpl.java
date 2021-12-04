package com.api.transaction.service;

import com.api.transaction.exception.BackendException;
import com.api.transaction.model.Account;
import com.api.transaction.model.Transaction;
import com.api.transaction.repository.AccountRepository;
import com.api.transaction.repository.TransactionRepository;
import com.api.transaction.util.DecoderUtil;
import com.api.transaction.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {
    private DecoderUtil decoderUtil;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public AccountServiceImpl(DecoderUtil decoderUtil, AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {
        this.decoderUtil = decoderUtil;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Map> updateAccount(Transaction transaction) {
        try {
            decoderUtil.decodeRequest(transaction);
            Account sourceAccount =
                    accountRepository.findByAccountNumber(transaction.getSourceAccountNumber());
            Account destinationAccount =
                    accountRepository.findByAccountNumber(transaction.getDestinationAccountNumber());
            transferAmountAndUpdateTransaction(transaction, sourceAccount, destinationAccount);
            return new ResponseEntity<>(prepareSuccessResponse(), HttpStatus.OK);
        } catch (Exception ex) {
            BackendException backendException =
                    BackendException.create("503", "service unavailable", ex);
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("code", backendException.getCode());
            errorMap.put("message", backendException.getMessage());
            return new ResponseEntity<>(errorMap, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    private Map<String, String> prepareSuccessResponse() {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("code", "200");
        responseMap.put("message", "transaction happened successfully");
        return responseMap;
    }

    private void transferAmountAndUpdateTransaction(Transaction transaction, Account sourceAccount, Account destinationAccount) {
        if (transaction.getTransactionType().equals("TRANSFER")) {
            AccountValidator.validate(sourceAccount, destinationAccount, transaction);
            sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());
            destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getAmount());
            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);
            transaction.setTransactionTime(new Date());
            transactionRepository.save(transaction);
        } else if (transaction.getTransactionType().equals("REVERSE")) {
            AccountValidator.validate(sourceAccount, destinationAccount, transaction);
            sourceAccount.setBalance(sourceAccount.getBalance() + transaction.getAmount());
            destinationAccount.setBalance(destinationAccount.getBalance() - transaction.getAmount());
            accountRepository.save(sourceAccount);
            accountRepository.save(destinationAccount);
            transaction.setTransactionTime(new Date());
            transactionRepository.save(transaction);
        }
    }
}
