package com.example.mycli.repository;

import com.example.mycli.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findTransactionById(String id);

    @Query(value = "SELECT NEXTVAL('transaction_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
