package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.validator.ConstraintValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

@SpringBootTest
public class ConstraintValidatorTests {

    @Autowired
    private ConstraintValidator constraintValidator;

    @DisplayName("user validate 에러 테스트")
    @Test
    public void userInvalid() {
        User user = new User();

        ConstraintViolationException constraintViolationException =
                Assertions.assertThrows(ConstraintViolationException.class, () -> {
            constraintValidator.validate(user);
        });

        Assertions.assertEquals("age: 반드시 값이 있어야 합니다., name: 반드시 값이 있어야 합니다.", constraintViolationException.getMessage());
    }

    @DisplayName("user validate 정상 테스트")
    @Test
    public void userValid() {
        User user = new User("test", 20);
        Assertions.assertDoesNotThrow(() -> {
            constraintValidator.validate(user);
        });
    }

    @DisplayName("user 테스트")
    @Test
    public void user() {
        User user = new User();
        user.setName(null);

    }
}
