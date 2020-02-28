package com.example.demo.exception;

public class ApiValidationError extends ApiSubError {
    private String message;

    public ApiValidationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
