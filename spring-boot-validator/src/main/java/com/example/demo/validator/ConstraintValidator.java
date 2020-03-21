package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.Set;

@Component
public class ConstraintValidator<T> {
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    public void validate(T claz) {
        validator.validate(claz);
        Set<ConstraintViolation<T>> validators = validator.validate(claz);

        if (validators.size() > 0) {
            throw new ConstraintViolationException(validators);
        }
    }
}
