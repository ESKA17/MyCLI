package com.example.mycli.repository;

import com.example.mycli.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {

}