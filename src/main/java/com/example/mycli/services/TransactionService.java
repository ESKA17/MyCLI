package com.example.mycli.services;

import com.example.mycli.server.AccountType;

public interface TransactionService {
    void addTransaction(String operation, AccountType accountType, String id, long clientID, double amount);

}
