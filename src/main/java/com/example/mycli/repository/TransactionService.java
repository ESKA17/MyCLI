package com.example.mycli.repository;

import com.example.mycli.server.AccountType;
import com.example.mycli.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionInterface {
    private final TransactionRepository transactionRepository;
    private static long lastTransactionNumber = 1;


    @Override
    public void addTransaction(String operation, AccountType accountType, String id, String clientID, double amount) {
        Transaction transaction = new Transaction(lastTransactionNumber, operation, accountType, id, clientID, amount);
        transactionRepository.save(transaction);
        incrementLastTransactionNumber();
    }
    private void incrementLastTransactionNumber() {
        lastTransactionNumber++;
    }


}
