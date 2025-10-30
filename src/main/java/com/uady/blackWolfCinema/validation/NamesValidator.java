package com.uady.blackWolfCinema.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NamesValidator implements ConstraintValidator<ValidNames, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            addConstraintViolation(context, "El nombre no puede estar vacío");
            return false;
        }

        // Solo permite caracteres alfabéticos y espacios
        if (!value.matches("^[a-záéíóúñA-ZÁÉÍÓÚÑ\\s]+$")) {
            addConstraintViolation(context, "El nombre solo debe contener caracteres alfabéticos");
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
