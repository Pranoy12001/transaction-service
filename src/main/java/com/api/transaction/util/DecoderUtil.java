package com.api.transaction.util;

import com.api.transaction.exception.ClientException;
import com.api.transaction.model.Transaction;
import com.api.transaction.model.TransactionPayload;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static com.api.transaction.constant.AccountConstant.*;

/**
 * Decoder Util
 */
@Component
public class DecoderUtil {

    /**
     * this method decode the payload and prepare transaction entity
     *
     * @param payload
     * @return
     */
    public static Transaction decodeRequest(TransactionPayload payload) {
        Transaction transaction = new Transaction();
        transaction.setRequestId(payload.getRequestId());
        transaction.setRequester(payload.getRequester());
        byte[] transactionType =
                Base64.getDecoder().decode(payload.getTransactionType());
        transaction.setTransactionType(new String(transactionType));
        if (!isValidTransactionType(transaction)) {
            throw ClientException
                    .create(String.valueOf(HttpStatus.BAD_REQUEST), INVALID_REQUEST_MESSAGE, null);
        }

        byte[] sourceAccountNumber =
                Base64.getDecoder().decode(payload.getSourceAccountNumber());
        transaction.setSourceAccountNumber(new String(sourceAccountNumber));

        byte[] amount =
                Base64.getDecoder().decode(payload.getAmount());
        transaction.setAmount(parseDouble(new String(amount)));

        byte[] destinationAccountNumber =
                Base64.getDecoder().decode(payload.getDestinationAccountNumber());
        transaction.setDestinationAccountNumber(new String(destinationAccountNumber));
        transaction.setNote(payload.getNote());
        return transaction;
    }

    private static boolean isValidTransactionType(Transaction transaction) {
        if (transaction.getTransactionType().equals(TRANSFER)) {
            return true;
        } else if (transaction.getTransactionType().equals(REVERSE)){
            return true;
        } else {
            return false;
        }
    }

    public static double parseDouble(String amount) {
        try {
            return Double.parseDouble(amount);
        } catch (NumberFormatException ex) {
            throw ClientException.create(String.valueOf(HttpStatus.BAD_REQUEST.value()), INVALID_REQUEST_MESSAGE, ex);
        }
    }
}
