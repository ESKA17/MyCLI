package com.example.mycli.dao;

import com.example.mycli.server.Account;
import com.example.mycli.server.AccountType;
import com.example.mycli.server.AccountWithdraw;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepositoryDAO extends CrudRepository<Account, String> {

    List<Account> getClientAccounts(String clientID);

    void createNewAccount(Account account);

    void updateAccount(Account account);

    List<Account> getClientAccountsByType(String clientID, AccountType accountType);

    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);

    Account getClientAccount(String clientID, String accountID);
}
