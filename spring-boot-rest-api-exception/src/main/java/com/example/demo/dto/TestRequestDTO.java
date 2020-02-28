package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TestRequestDTO {
    @Size(max = 5)
    @NotEmpty
    @NotNull
    private String id;

    @Size(max = 10)
    @NotEmpty
    @NotNull
    private String value;

    public TestRequestDTO(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public TestRequestDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
