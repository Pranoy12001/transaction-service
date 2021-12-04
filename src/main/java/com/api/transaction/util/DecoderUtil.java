package com.api.transaction.util;

import com.api.transaction.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class DecoderUtil {
    public void decodeRequest(Transaction transaction) {
        byte[] transactionType =
                Base64.getDecoder().decode(transaction.getEncodedTransactionType());
        transaction.setTransactionType(new String(transactionType));

        byte[] sourceAccountNumber =
                Base64.getDecoder().decode(transaction.getEncodedSourceAccountNumber());
        transaction.setSourceAccountNumber(new String(sourceAccountNumber));

        byte[] amount =
                Base64.getDecoder().decode(transaction.getEncodedAmount());
        transaction.setAmount(Double.parseDouble(new String(amount)));

        byte[] destinationAccountNumber =
                Base64.getDecoder().decode(transaction.getEncodedDestinationAccountNumber());
        transaction.setDestinationAccountNumber(new String(destinationAccountNumber));
    }
}
