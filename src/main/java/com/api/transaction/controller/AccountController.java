package com.api.transaction.controller;

import com.api.transaction.exception.ClientException;
import com.api.transaction.model.TransactionPayload;
import com.api.transaction.service.AccountService;
import com.api.transaction.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

import static com.api.transaction.constant.AccountConstant.*;

/**
 * Account Controller
 */
@Controller
public class AccountController {
    private AccountService accountService;

    /**
     * Autowired constructor
     *
     * @param accountService
     */
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * this controller is used for to test the application is running or not
     *
     * @return response {@link ResponseEntity}
     */
    @GetMapping("/test-application")
    public ResponseEntity<String> testApplication() {
        return new ResponseEntity<>("Application is running...", HttpStatus.OK);
    }

    /**
     * this controller is used to trigger transaction
     *
     * @param payload payload of the request{@link TransactionPayload}
     * @return code and message as a Map {@link ResponseEntity}
     */
    @PostMapping("/transfer-money")
    public ResponseEntity<Map> triggerTransaction(@RequestBody TransactionPayload payload) {
        try {
            RequestValidator.validate(payload);
        } catch (ClientException ex) {
            return getErrorResponseEntity(ex);
        }
        return accountService.updateAccount(payload);
    }

    private ResponseEntity<Map> getErrorResponseEntity(Exception ex) {
        ClientException clientException =
                ClientException.create(String.valueOf(HttpStatus.BAD_REQUEST.value()), INVALID_REQUEST_MESSAGE, ex);
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(CODE, clientException.getCode());
        errorMap.put(MESSAGE, clientException.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
