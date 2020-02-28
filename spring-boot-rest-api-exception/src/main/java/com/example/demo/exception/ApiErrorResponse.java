package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiErrorResponse {
    private HttpStatus httpStatus;
    private String message;
    private List< ? extends ApiSubError> subErrors;

    public ApiErrorResponse(HttpStatus httpStatus, String message, List< ? extends ApiSubError> subErrors) {
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

    public List< ? extends ApiSubError> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List< ? extends ApiSubError> subErrors) {
        this.subErrors = subErrors;
    }
}
