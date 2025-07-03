package com.maykealisson.webflux.controller.exceptions;


import com.maykealisson.webflux.exception.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
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

    @ExceptionHandler(WebExchangeBindException.class)
    ResponseEntity<Mono<StandardError>> validateError(
            WebExchangeBindException ex,
            ServerHttpRequest request
    ){
        var error = new ValidationError(
                LocalDateTime.now(),
                String.valueOf(request.getPath()),
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                "error validation attributes"
        );
        ex.getBindingResult().getFieldErrors()
                .forEach( it -> error.addError(it.getField(), it.getDefaultMessage()));

        return ResponseEntity.badRequest()
                .body(Mono.just(error));
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Mono<StandardError>> notFoundError(
            NotFoundException ex,
            ServerHttpRequest request
    ){
        var response = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .path(String.valueOf(request.getPath()))
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Mono.just(response));
    }

    private String verifyDupKey(String message){
        if(message.contains("email dup key")) return "E-mail já cadastrado";
        return message;
    }
}
