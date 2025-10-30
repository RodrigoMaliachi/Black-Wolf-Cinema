package com.uady.blackWolfCinema.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            addConstraintViolation(context, "La contraseña no puede estar vacía");
            return false;
        }

        if (value.length() < 8) {
            addConstraintViolation(context, "La contraseña debe tener al menos 8 caracteres");
            return false;
        }

        if (!value.matches(".*[a-zA-Z].*")) {
            addConstraintViolation(context, "La contraseña debe contener letras");
            return false;
        }

        if (!value.matches(".*[0-9].*")) {
            addConstraintViolation(context, "La contraseña debe contener números");
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
