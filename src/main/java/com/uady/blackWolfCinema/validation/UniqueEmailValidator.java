package com.uady.blackWolfCinema.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uady.blackWolfCinema.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Se valida en otro validador @NotNull
        }

        // Verificar si el email ya existe en la base de datos
        boolean exists = userService.existsByEmail(value);
        
        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El correo electrónico ya está registrado")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}
