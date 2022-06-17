package com.example.mycli.services;

import com.example.mycli.repository.TransactionRepository;
import com.example.mycli.server.AccountType;
import com.example.mycli.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public void addTransaction(String operation, AccountType accountType, String id, long clientID, double amount) {
        long lastTransactionNumber = transactionRepository.getNextSeriesId();
        Transaction transaction = new Transaction(lastTransactionNumber, operation, accountType, id, clientID, amount);
        transactionRepository.save(transaction);
    }


}
