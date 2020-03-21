package com.example.demo.model;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

public class User {
    @NotNull
    private String name;

    @NotNull
    private Integer age;

    public User() {
    }

    public User(@NotNull String name, @NotNull Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
