package com.maykealisson.webflux.domain.request;

import com.maykealisson.webflux.validation.Personalizada;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @Personalizada
        @Size(min = 3, max = 50)
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        @Size(min = 6, max = 50)
        String password
) {
}
