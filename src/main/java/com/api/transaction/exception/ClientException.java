package com.api.transaction.exception;

public class ClientException extends RuntimeException{
    private String code;
    private String message;

    public ClientException(String s, Throwable throwable, String code, String message) {
        super(s, throwable);
        this.code = code;
        this.message = message;
    }

    public static ClientException create(String code, String message, Exception ex) {
        return new ClientException(message, ex, code, message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
