package com.posfiap.techfood.infra.exceptions;

public class InvalidPasswordException extends RuntimeException{
     public InvalidPasswordException(String message) {
            super(message);
        }
    }

