package com.example.mycli.exceptions;

public class AccountCheckLoginPassword extends RuntimeException{
    public AccountCheckLoginPassword() {
        super("Please check your login and/or password");
    }
}