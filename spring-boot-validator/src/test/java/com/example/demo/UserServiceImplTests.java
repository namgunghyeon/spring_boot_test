package com.example.demo;

import com.example.demo.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

@SpringBootTest
public class UserServiceImplTests {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @DisplayName("model invalid service 테스트")
    @Test
    public void modelInvalidService() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userServiceImpl.newUser(null, null);
        });

    }

    @DisplayName("model valid service 테스트")
    @Test
    public void modelvalidService() {
        Assertions.assertDoesNotThrow(() -> {
            userServiceImpl.newUser("test", 20);
        });
    }
}
