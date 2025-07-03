package com.maykealisson.webflux.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class NotFoundException extends RuntimeException implements Serializable {
    private static final long serialUID = 1L;

    private final String errorCode = "404";
    private final String errorMsg;

    public NotFoundException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }
}
