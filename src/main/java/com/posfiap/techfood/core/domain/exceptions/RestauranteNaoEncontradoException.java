package com.posfiap.techfood.core.domain.exceptions;

public class RestauranteNaoEncontradoException extends RuntimeException {
    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }
}
