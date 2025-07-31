package com.posfiap.techfood.infra.exceptions;

public class InvalidUserNameAccount extends RuntimeException{
    public InvalidUserNameAccount(String message){
        super(message);
    }
}
