package com.example.mycli.repository;

import com.example.mycli.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {
    List<Transaction> findTransactionById(String id);
}
