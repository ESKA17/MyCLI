package com.example.mycli;

import java.util.*;

public class MemoryAccountDAO implements AccountDAO {
    private final List<Account> accountList;

    public MemoryAccountDAO() {
        this.accountList = new ArrayList<Account>() ;
    }

    @Override
    public List<Account> getClientAccounts(String clientID) {
        return null;
    }

    @Override
    public void createNewAccount(Account account) {
        this.accountList.add(account);
    }

    @Override
    public void updateAccount(Account account) {
        int id = Integer.parseInt(account.getId());
        this.accountList.set(id, account);
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType) {
        return null;
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        return null;
    }

    @Override
    public Account getClientAccount(String clientID, String accountID) {
        return null;
    }
}
