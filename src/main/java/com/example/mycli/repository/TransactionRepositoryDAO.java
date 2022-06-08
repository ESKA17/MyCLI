package com.example.mycli.repository;

import com.example.mycli.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface TransactionRepositoryDAO extends CrudRepository<Transaction, String> {
    Transaction getTransactionByTransactionID(long id);
}
