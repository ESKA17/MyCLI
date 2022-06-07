package com.example.mycli.dao;

import com.example.mycli.server.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepositoryDAO extends CrudRepository<Transaction, String> {
    Transaction getTransactionByTransactionID(long id);
}
