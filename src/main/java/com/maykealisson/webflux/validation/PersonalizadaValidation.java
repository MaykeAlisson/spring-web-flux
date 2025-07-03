package com.maykealisson.webflux.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PersonalizadaValidation implements ConstraintValidator<Personalizada, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Caso retorne true vai dar a sua msg de erro personalizada
        // aqui devemos aplicar nossa regra de validacao
        return Objects.isNull(value) || value.trim().length() == value.length();
    }
}
