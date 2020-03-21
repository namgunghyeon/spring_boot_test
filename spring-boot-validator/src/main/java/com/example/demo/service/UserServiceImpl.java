package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Service
public class UserServiceImpl implements UserService {
    @Override
    public User newUser(@NotNull String name, @NotNull Integer age) {
        return new User(name, age);
    }
}
