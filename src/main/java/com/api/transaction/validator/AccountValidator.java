package com.api.transaction.validator;

import com.api.transaction.exception.BackendException;
import com.api.transaction.model.Account;
import com.api.transaction.model.Transaction;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static com.api.transaction.constant.AccountConstant.*;

public class AccountValidator {
    public static void validate(Account sourceAccount, Account destinationAccount,
                                Transaction transaction) {
        if (Objects.isNull(sourceAccount) || Objects.isNull(destinationAccount)) {
            throw BackendException
                    .create(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), ACCOUNT_NOT_AVAILABLE_MESSAGE, null);
        } else if (sourceAccount.getStatus() != 1 || destinationAccount.getStatus() != 1){
            throw BackendException
                    .create(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), ACCOUNT_NOT_ACTIVE_MESSAGE, null);
        } else {
            if (transaction.getTransactionType().equals(TRANSFER)
                    && sourceAccount.getBalance() < transaction.getAmount()) {
                throw BackendException
                        .create(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), INSUFFICIENT_MESSAGE, null);
            } else if (transaction.getTransactionType().equals(REVERSE)
                    && destinationAccount.getBalance() < transaction.getAmount()) {
                throw BackendException
                        .create(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), INSUFFICIENT_MESSAGE, null);
            }
        }
    }
}
