package com.api.transaction.validator;

import com.api.transaction.exception.ClientException;
import com.api.transaction.model.TransactionPayload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static com.api.transaction.constant.AccountConstant.INVALID_REQUEST_MESSAGE;

/**
 * Request Validator
 */
public class RequestValidator {
    /**
     * validate the incoming payload
     *
     * @param payload
     */
    public static void validate(TransactionPayload payload) {
        if (Objects.isNull(payload)) {
            throw ClientException
                    .create(String.valueOf(HttpStatus.BAD_REQUEST.value()), INVALID_REQUEST_MESSAGE, null);
        } else if (StringUtils.isBlank(payload.getRequestId())
                || StringUtils.isBlank(payload.getRequester())
                || StringUtils.isBlank(payload.getTransactionType())
                || StringUtils.isBlank(payload.getSourceAccountNumber())
                || StringUtils.isBlank(payload.getAmount())
                || StringUtils.isBlank(payload.getDestinationAccountNumber())
                || StringUtils.isBlank(payload.getNote())) {
            throw ClientException
                    .create(String.valueOf(HttpStatus.BAD_REQUEST.value()), INVALID_REQUEST_MESSAGE, null);
        }
    }
}
