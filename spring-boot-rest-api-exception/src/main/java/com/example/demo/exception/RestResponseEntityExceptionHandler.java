package com.example.demo.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = {UnrecognizedPropertyException.class})
    protected ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex) {
        List<ApiValidationError> errors = new ArrayList<>();

        final String error = "JSON parse error: Unrecognized field " + "[ " + ex.getPropertyName() + " ]";
        ApiValidationError apiValidationError = new ApiValidationError(error);
        errors.add(apiValidationError);

        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Bad request", errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND, "Not found"), HttpStatus.NOT_FOUND);
    }
}
