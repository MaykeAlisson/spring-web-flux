package com.maykealisson.webflux.domain.service;

import com.maykealisson.webflux.domain.entity.User;
import com.maykealisson.webflux.domain.mapper.UserMapper;
import com.maykealisson.webflux.domain.request.UserRequest;
import com.maykealisson.webflux.domain.response.UserResponse;
import com.maykealisson.webflux.output.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;

    @Test
    void save(){
        var request = new UserRequest("anyName", "any@email.com", "123456");
        var entity = User.builder().build();
        var response = new UserResponse("1", "anyName");

        when(mapper.toEntity(any())).thenReturn(entity);
        when(repository.save(any())).thenReturn(Mono.just(entity));
        when(mapper.toResponse(any())).thenReturn(response);

        var result = service.save(request);

        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(any());
    }

    @Test
    void findById(){
        var entity = User.builder().build();
        var response = new UserResponse("1", "anyName");

        when(repository.findById(any())).thenReturn(Mono.just(entity));
        when(mapper.toResponse(any())).thenReturn(response);

        var result = service.findById("1");

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == UserResponse.class)
                .expectComplete()
                .verify();

        verify(repository, times(1)).findById(any());
    }

    @Test
    void findAll(){
        var entity = User.builder().build();
        var response = new UserResponse("1", "anyName");

        when(repository.findAll()).thenReturn(Flux.just(entity));
        when(mapper.toResponse(any())).thenReturn(response);

        var result = service.findAll();

        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        verify(repository, times(1)).findAll();
    }

    @Test
    void update(){
        var request = new UserRequest("anyName", "any@email.com", "123456");
        var entity = User.builder().build();
        var response = new UserResponse("1", "anyName");

        when(mapper.toEntity(any(), any())).thenReturn(entity);
        when(repository.save(any())).thenReturn(Mono.just(entity));
        when(repository.findById(any())).thenReturn(Mono.just(entity));
        when(mapper.toResponse(any())).thenReturn(response);

        var result = service.update("123", request);

        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void delete(){
        var entity = User.builder().build();

        when(repository.delete(any())).thenReturn(Mono.just(entity));

        var result = service.delete("1234");

        StepVerifier.create(result)
                .expectComplete()
                .verify();

        verify(repository, times(1)).delete(any());
    }

}