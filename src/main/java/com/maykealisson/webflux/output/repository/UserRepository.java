package com.maykealisson.webflux.output.repository;

import com.maykealisson.webflux.domain.entity.User;
import com.maykealisson.webflux.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<User> save(final User user) {
        return reactiveMongoTemplate.save(user);
    }

    public Mono<User> findById(final String id) {
        return reactiveMongoTemplate.findById(id, User.class)
                .switchIfEmpty(Mono.error(
                        new NotFoundException(format("User with id %s not found", id)
                        )));
    }

    public Flux<User> findAll() {
        return reactiveMongoTemplate.findAll(User.class);
    }

    public Mono<User> delete(final String id) {
        Query query = new Query();
        Criteria where = Criteria.where("id").is(id);
        return reactiveMongoTemplate.findAndRemove(query.addCriteria(where), User.class)
                .switchIfEmpty(Mono.error(
                        new NotFoundException(format("User with id %s not found", id)
                        )));
    }
}
