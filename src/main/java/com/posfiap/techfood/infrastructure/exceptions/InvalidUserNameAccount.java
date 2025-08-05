package com.posfiap.techfood.infrastructure.exceptions;

public class InvalidUserNameAccount extends RuntimeException{
    public InvalidUserNameAccount(String message){
        super(message);
    }
}
