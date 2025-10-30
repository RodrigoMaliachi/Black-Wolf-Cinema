package com.uady.blackWolfCinema.validation;

import java.time.LocalDate;

import com.uady.blackWolfCinema.model.Show;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DatesValidator implements ConstraintValidator<ValidDates, Show> {

    @Override
    public boolean isValid(Show value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        LocalDate showDate = value.getShowDate();

        if (showDate == null) {
            addConstraintViolation(context, "La fecha de inicio no es válida");
            return false;
        }

        // Validar que la fecha no sea en el pasado
        if (showDate.isBefore(LocalDate.now())) {
            addConstraintViolation(context, "La fecha de la función no puede ser en el pasado");
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
