package com.example.mycli.dao;

import com.example.mycli.server.Account;
import com.example.mycli.server.AccountType;
import com.example.mycli.server.AccountWithdraw;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryAccountDAO implements AccountDAO {
    private final List<Account> accountList;

    public MemoryAccountDAO() {
        accountList = new ArrayList<>();
    }

    @Override
    public List<Account> getClientAccounts(String clientID) {
        List<Account> out = new ArrayList<>();
        for (Account account: accountList) {
            if (Objects.equals(account.getClientID(), clientID)) {
                out.add(account);
            }
        }
        return out;
    }

    @Override
    public void createNewAccount(Account account) {
        this.accountList.add(account);
    }

    @Override
    public void updateAccount(Account account) {
        int id = Integer.parseInt(account.getId())%100000-1;
        this.accountList.set(id, account);
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType) {
        List<Account> clientAccounts = getClientAccounts(clientID);
        List<Account> out = new ArrayList<>();
        for (Account account: clientAccounts) {
            if (account.getAccountType() == accountType) {
                out.add(account);
            }
        }
        return out;
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        if (Integer.parseInt(accountID)%100000 > accountList.size()) return null;
        Account out = getClientAccount(clientID, accountID);
        if (out.isWithdrawalAllowed()) return (AccountWithdraw) out;
        return null;
    }

    @Override
    public Account getClientAccount(String clientID, String accountID) {
        if (Integer.parseInt(accountID)%100000 > accountList.size()) return null;
        Account out = accountList.get(Integer.parseInt(accountID)%100000-1);
        if (Objects.equals(out.getClientID(), clientID)) return out;
        return null;
    }
}
