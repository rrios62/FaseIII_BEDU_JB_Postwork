package org.bedu.java.backend.Postwork.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<GlobalInterface, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length()<3 || value.length()>50&&value.matches("^[A-Za-z0-9+_.-]");
    }
}