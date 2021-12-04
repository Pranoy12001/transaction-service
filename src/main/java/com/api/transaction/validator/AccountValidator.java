package com.api.transaction.validator;

import com.api.transaction.exception.BackendException;
import com.api.transaction.model.Account;
import com.api.transaction.model.Transaction;

import java.util.Objects;

public class AccountValidator {
    public static void validate(Account sourceAccount, Account destinationAccount,
                                Transaction transaction) {
        if (Objects.isNull(sourceAccount) || Objects.isNull(destinationAccount)) {
            throw BackendException.create("503", "source/destination account not available", null);
        } else if (sourceAccount.getStatus() != 1 || destinationAccount.getStatus() != 1){
            throw BackendException.create("503", "source/destination account not active", null);
        } else {
            if (transaction.getTransactionType().equals("TRANSFER")
                    && sourceAccount.getBalance() < transaction.getAmount()) {
                throw BackendException.create("503", "insufficient balance", null);
            } else if (transaction.getTransactionType().equals("REVERSE")
                    && destinationAccount.getBalance() < transaction.getAmount()) {
                throw BackendException.create("503", "insufficient balance", null);
            }
        }
    }
}
