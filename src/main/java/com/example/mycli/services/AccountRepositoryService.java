package com.example.mycli.services;

import com.example.mycli.model.Account;
import com.example.mycli.server.AccountType;
import com.example.mycli.server.AccountWithdraw;

import java.util.List;

public interface AccountRepositoryService {
    List<Account> getClientAccounts(long clientID);

    void createNewAccount(Account account);

    void updateAccount(Account account);

    List<Account> getClientAccountsByType(long clientID, AccountType accountType);

    AccountWithdraw getClientWithdrawAccount(long clientID, String accountID);

    Account getClientAccount(long clientID, String accountID);
    Account getAccount(String accountID);
}
