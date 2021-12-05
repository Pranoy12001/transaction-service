package com.api.transaction.service.impl;

import com.api.transaction.exception.BackendException;
import com.api.transaction.exception.ClientException;
import com.api.transaction.model.Account;
import com.api.transaction.model.Transaction;
import com.api.transaction.model.TransactionPayload;
import com.api.transaction.repository.AccountRepository;
import com.api.transaction.repository.TransactionRepository;
import com.api.transaction.service.AccountService;
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

import static com.api.transaction.constant.AccountConstant.*;

/**
 * Account Service implementation class
 */
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    /**
     * autowired constructor
     *
     * @param accountRepository
     * @param transactionRepository
     */
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * perform the update operation
     *
     * @param payload of the request {@link TransactionPayload}
     * @return response entity {@link  ResponseEntity}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Map> updateAccount(TransactionPayload payload) {
        try {
            Transaction transaction = DecoderUtil.decodeRequest(payload);
            Account sourceAccount =
                    accountRepository.findByAccountNumber(transaction.getSourceAccountNumber());
            Account destinationAccount =
                    accountRepository.findByAccountNumber(transaction.getDestinationAccountNumber());
            transferAmountAndUpdateTransaction(transaction, sourceAccount, destinationAccount);
            return new ResponseEntity<>(prepareSuccessResponse(), HttpStatus.OK);
        } catch (Exception ex) {
            if (ex instanceof ClientException) {
                ClientException clientException =
                        ClientException.create(String.valueOf(HttpStatus.BAD_REQUEST.value()), INVALID_REQUEST_MESSAGE, ex);
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put(CODE, clientException.getCode());
                errorMap.put(MESSAGE, clientException.getMessage());
                return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
            } else if (ex instanceof BackendException) {
                BackendException backendException =
                        BackendException.create(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), ex.getMessage(), ex);
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put(CODE, backendException.getCode());
                errorMap.put(MESSAGE, backendException.getMessage());
                return new ResponseEntity<>(errorMap, HttpStatus.SERVICE_UNAVAILABLE);
            } else {
                BackendException backendException =
                        BackendException.create(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), SERVICE_UNAVAILABLE_MESSAGE, ex);
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put(CODE, backendException.getCode());
                errorMap.put(MESSAGE, backendException.getMessage());
                return new ResponseEntity<>(errorMap, HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
    }

    private Map<String, String> prepareSuccessResponse() {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put(CODE, String.valueOf(HttpStatus.OK.value()));
        responseMap.put(MESSAGE, SUCCESSFUL_TRANSACTION_MESSAGE);
        return responseMap;
    }

    private void transferAmountAndUpdateTransaction(Transaction transaction, Account sourceAccount, Account destinationAccount) {
        if (transaction.getTransactionType().equals(TRANSFER)) {
            AccountValidator.validate(sourceAccount, destinationAccount, transaction);
            sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());
            destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getAmount());
        } else  {
            AccountValidator.validate(sourceAccount, destinationAccount, transaction);
            sourceAccount.setBalance(sourceAccount.getBalance() + transaction.getAmount());
            destinationAccount.setBalance(destinationAccount.getBalance() - transaction.getAmount());
        }
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);
        transaction.setTransactionTime(new Date());
        transactionRepository.save(transaction);
    }
}
