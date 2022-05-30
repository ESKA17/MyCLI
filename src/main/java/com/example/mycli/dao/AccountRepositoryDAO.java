package com.example.mycli.dao;

import com.example.mycli.server.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepositoryDAO extends CrudRepository<Account, String> {


}
