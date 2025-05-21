package com.posfiap.techfood.exceptions;

public class InvalidUserNameAccount extends RuntimeException{
    public InvalidUserNameAccount(String message){
        super(message);
    }
}
