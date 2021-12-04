package com.api.transaction.exception;

public class BackendException extends RuntimeException{
    private String Code;
    private String message;

    public BackendException(String s, Throwable throwable, String code, String message) {
        super(s, throwable);
        Code = code;
        this.message = message;
    }

    public static BackendException create (String code, String message, Exception ex) {
        return new BackendException(message, ex, code, message);
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
