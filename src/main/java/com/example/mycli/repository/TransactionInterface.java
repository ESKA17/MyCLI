package com.example.mycli.repository;

import com.example.mycli.server.AccountType;

public interface TransactionInterface {
    void addTransaction(String operation, AccountType accountType, String id, String clientID, double amount);

}
