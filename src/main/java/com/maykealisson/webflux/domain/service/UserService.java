package com.maykealisson.webflux.domain.service;

import com.maykealisson.webflux.domain.mapper.UserMapper;
import com.maykealisson.webflux.domain.request.UserRequest;
import com.maykealisson.webflux.domain.response.UserResponse;
import com.maykealisson.webflux.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Mono<UserResponse> save(final UserRequest request) {
        return repository.save(mapper.toEntity(request))
                .map(mapper::toResponse);
    }

    public Mono<UserResponse> findById(final String id) {
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    public Flux<UserResponse> findAll() {
        return repository.findAll()
                .map(mapper::toResponse);
    }

    public Mono<UserResponse> update(final String id, final UserRequest request) {
        return repository.findById(id)
                .map(user -> mapper.toEntity(request, user))
                .flatMap(repository::save)
                .map(mapper::toResponse);
    }

    public Mono<Void> delete(final String id) {
        return repository.delete(id).then();
    }
}
