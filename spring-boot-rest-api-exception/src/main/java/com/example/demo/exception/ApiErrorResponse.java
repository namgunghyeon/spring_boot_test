package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiErrorResponse {
    private HttpStatus httpStatus;
    private String message;
    //private List< ? extends ApiSubError> subErrors;
    private List<String> subErrors;

    public ApiErrorResponse(HttpStatus httpStatus, String message, List<String> subErrors) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.subErrors = subErrors;
    }

    public ApiErrorResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ApiErrorResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<String> subErrors) {
        this.subErrors = subErrors;
    }
}
