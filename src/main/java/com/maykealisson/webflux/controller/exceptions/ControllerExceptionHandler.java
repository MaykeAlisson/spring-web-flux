package com.maykealisson.webflux.controller.exceptions;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<Mono<StandardError>> duplicateKeyException(
            DuplicateKeyException ex,
            ServerHttpRequest request
    ){
        var response = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .path(String.valueOf(request.getPath()))
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(verifyDupKey(ex.getMessage()))
                .build();
        return ResponseEntity.badRequest()
                .body(Mono.just(response));
    }

    private String verifyDupKey(String message){
        if(message.contains("email dup key")) return "E-mail já cadastrado";
        return message;
    }
}
