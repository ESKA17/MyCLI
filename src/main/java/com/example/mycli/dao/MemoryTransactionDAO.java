package com.example.mycli.dao;

import com.example.mycli.server.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryTransactionDAO implements TransactionDAO{

    private final List<Transaction> transactions;

    public MemoryTransactionDAO() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
