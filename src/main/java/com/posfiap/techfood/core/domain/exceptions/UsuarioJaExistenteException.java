package com.posfiap.techfood.core.domain.exceptions;

public class UsuarioJaExistenteException extends RuntimeException {
    public UsuarioJaExistenteException(String message) {
        super(message);
    }
}
