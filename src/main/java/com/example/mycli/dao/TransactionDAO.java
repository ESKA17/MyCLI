package com.example.mycli.dao;

import com.example.mycli.server.AccountType;
import com.example.mycli.server.Transaction;
import org.springframework.stereotype.Service;
import java.util.List;

public interface TransactionDAO {

    List<Transaction> getTransactions();

    void addTransaction(String operation, AccountType accountType, String id, String clientID, double amount);

}
