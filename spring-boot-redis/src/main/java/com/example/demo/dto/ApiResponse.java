package com.example.demo.dto;

import javax.validation.constraints.NotNull;

public class ApiResponse<T> {
    @NotNull T data;

    public ApiResponse(@NotNull T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
