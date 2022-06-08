package com.example.mycli.repository;

import com.example.mycli.server.AccountType;
import com.example.mycli.model.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DatabaseTransactionDAO implements TransactionDAO {
    private final TransactionRepositoryDAO transactionRepositoryDAO;
    private static long lastTransactionNumber = 1;

    @Override
    public List<Transaction> getTransactions() {
        return (List<Transaction>) transactionRepositoryDAO.findAll();
    }

    @Override
    public void addTransaction(String operation, AccountType accountType, String id, String clientID, double amount) {
        Transaction transaction = new Transaction(lastTransactionNumber, operation, accountType, id, clientID, amount);
        transactionRepositoryDAO.save(transaction);
        incrementLastTransactionNumber();
    }
    private void incrementLastTransactionNumber() {
        lastTransactionNumber++;
    }


}
