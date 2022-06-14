package com.example.mycli.repository;

import com.example.mycli.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    @Query(value = "SELECT NEXTVAL('account_seq')", nativeQuery = true)
    Long getNextSeriesId();

    Account findAccountById(String id);

}
