package com.maykealisson.webflux.domain.request;

public record UserRequest(
        String name,
        String email,
        String password
) {
}
