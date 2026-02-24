package com.example.demo.exception;

public class UserException extends RuntimeException {
    private String code;
    private int statusCode;

    public UserException(String message) {
        super(message);
        this.code = "USER_ERROR";
        this.statusCode = 400;
    }

    public UserException(String message, String code) {
        super(message);
        this.code = code;
        this.statusCode = 400;
    }

    public UserException(String message, String code, int statusCode) {
        super(message);
        this.code = code;
        this.statusCode = statusCode;
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
        this.code = "USER_ERROR";
        this.statusCode = 400;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
