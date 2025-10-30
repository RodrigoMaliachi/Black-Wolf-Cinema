package com.uady.blackWolfCinema.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = NamesValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNames {
    String message() default "El nombre solo debe contener caracteres alfabéticos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
