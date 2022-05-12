package com.example.mycli.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
@AllArgsConstructor
public class Transaction {
    private AccountType accountType;
    private String id;
    private String clientID;
    private double amount;
}
