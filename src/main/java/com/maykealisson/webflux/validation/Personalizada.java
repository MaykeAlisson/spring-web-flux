package com.maykealisson.webflux.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = { PersonalizadaValidation.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Personalizada {

    String message() default "Minha mensagem";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
