package com.api.transaction.validator;

import com.api.transaction.exception.ClientException;
import com.api.transaction.model.Transaction;

import java.util.Objects;

public class RequestValidator {
    public static void validate(Transaction transaction) {
        if (Objects.isNull(transaction)) {
            throw ClientException.create("400", "invalid request", null);
        } else if (Objects.isNull(transaction.getRequestId())
                || Objects.isNull(transaction.getRequester())
                || Objects.isNull(transaction.getTransactionType())
                || Objects.isNull(transaction.getEncodedTransactionType())
                || Objects.isNull(transaction.getEncodedAmount())
                || Objects.isNull(transaction.getEncodedDestinationAccountNumber())
                || Objects.isNull(transaction.getNote())) {
            throw ClientException.create("400", "invalid request", null);
        }
    }
}
