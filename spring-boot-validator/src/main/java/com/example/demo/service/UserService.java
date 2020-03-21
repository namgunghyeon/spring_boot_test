package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface UserService {
    public User newUser(@NotNull String name, @NotNull Integer age);
}
