package com.example.mycli.server;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class Transaction {
    private AccountType accountType;
    private String id;
    private String clientID;
    private double amount;
}
