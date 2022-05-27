package com.example.mycli.dao;

import com.example.mycli.server.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCrudRepositoryDAO extends CrudRepository<Account, String> {

}
