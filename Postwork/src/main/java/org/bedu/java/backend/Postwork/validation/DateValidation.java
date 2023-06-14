package org.bedu.java.backend.Postwork.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidation implements ConstraintValidator<GlobalInterface, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("");//
    }
}
