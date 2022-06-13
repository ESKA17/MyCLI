package com.example.mycli.repository;

import com.example.mycli.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findTopByOrderByIdDesc();
    Account findAccountById(String id);
    void deleteAccountById(String id);

}
