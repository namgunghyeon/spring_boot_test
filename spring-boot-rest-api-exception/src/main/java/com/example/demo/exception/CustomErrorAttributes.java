package com.example.demo.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    private ObjectMapper objectMapper;

    @Autowired
    public CustomErrorAttributes(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        List<String> errors = new ArrayList<>();
        final String error1 = errorAttributes.get("message").toString();
        final String error2 = errorAttributes.get("path").toString();
        errors.add(error1 + "(path: " + error2 + ")");

        int status = Integer.valueOf(errorAttributes.get("status").toString());
        HttpStatus httpStatus = HttpStatus.valueOf(status);

        return objectMapper.convertValue(new ApiErrorResponse(httpStatus, httpStatus.toString(), errors), Map.class);
    }
}
