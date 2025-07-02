package com.maykealisson.webflux.controller.impl;

import com.maykealisson.webflux.controller.UserController;
import com.maykealisson.webflux.domain.request.UserRequest;
import com.maykealisson.webflux.domain.response.UserResponse;
import com.maykealisson.webflux.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Override
    public ResponseEntity<Mono<Void>> create(final UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request).then());
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(final String id) {
        return null;
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(final String id, final UserRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(final String id) {
        return null;
    }
}
