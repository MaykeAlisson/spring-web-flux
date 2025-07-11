package com.maykealisson.webflux.controller.impl;

import com.maykealisson.webflux.controller.UserController;
import com.maykealisson.webflux.domain.request.UserRequest;
import com.maykealisson.webflux.domain.response.UserResponse;
import com.maykealisson.webflux.domain.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Mono<Void>> create(@Valid final UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request).then());
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(final String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(final String id, final UserRequest request) {
        return ResponseEntity.ok().body(service.update(id, request));
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(final String id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}
