package com.maykealisson.webflux.output.repository;

import com.maykealisson.webflux.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<User> save(User user){
        return reactiveMongoTemplate.save(user);
    }
}
