package com.example.mycli.dao;

import com.example.mycli.server.Transaction;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TransactionDAO {

    List<Transaction> getTransactions();

    void addTransaction(Transaction transaction);

}
