package com.example.mycli.exceptions;

public class AuthenticationFailed extends RuntimeException{
    public AuthenticationFailed() {
        super("Please check login and/or password");
    }
}
