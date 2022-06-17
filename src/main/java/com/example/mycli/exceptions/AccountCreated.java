package com.example.mycli.exceptions;

public class AccountCreated extends RuntimeException{
    public AccountCreated(String input) {
        super("Account with login " + input + " was successfully registered");
    }
}