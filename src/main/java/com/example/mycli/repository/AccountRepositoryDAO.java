package com.example.mycli.repository;

import com.example.mycli.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepositoryDAO extends CrudRepository<Account, String> {

}
