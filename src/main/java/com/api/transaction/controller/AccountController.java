package com.api.transaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {

    @PostMapping("/test")
    public ResponseEntity<Map> testController(@RequestBody String body) {
        Map<String, String> response = new HashMap<>();
        response.put("code", "200");
        response.put("message", "transaction happened successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
