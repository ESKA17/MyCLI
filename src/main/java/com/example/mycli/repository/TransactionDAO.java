package com.example.mycli.repository;

import com.example.mycli.server.AccountType;
import com.example.mycli.model.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> getTransactions();

    void addTransaction(String operation, AccountType accountType, String id, String clientID, double amount);

}
