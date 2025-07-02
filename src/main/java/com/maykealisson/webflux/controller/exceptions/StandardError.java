package com.maykealisson.webflux.controller.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class StandardError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDateTime timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;
}
