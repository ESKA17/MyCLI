package com.example.mycli.dao;

import com.example.mycli.server.Account;
import com.example.mycli.server.AccountType;
import com.example.mycli.server.AccountWithdraw;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DatabaseAccountDAO implements AccountDAO{
    private final AccountCrudRepositoryDAO accountCrudRepositoryDAO;
    @Override
    public List<Account> getClientAccounts(String clientID) {
        List<Account> accountList = new ArrayList<>();
        Iterable<Account> tmpMemory = accountCrudRepositoryDAO.findAll();
        tmpMemory.forEach(account -> {
            if (Objects.equals(account.getClientID(), clientID)) accountList.add(account);
        });
        return accountList;
    }

    @Override
    public void createNewAccount(Account account) {
        accountCrudRepositoryDAO.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        String updateAccountID = account.getId();
        accountCrudRepositoryDAO.deleteById(updateAccountID);
        accountCrudRepositoryDAO.save(account);
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType) {
        List<Account> out = new ArrayList<>();
        List<Account> accountList = getClientAccounts(clientID);
        accountList.forEach(account -> {
            if (accountType == account.getAccountType()) out.add(account);
        });
        return out;
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        List<Account> accountList = new ArrayList<>(getClientAccounts(clientID));
        return (AccountWithdraw) accountList.stream().filter(account -> Objects.equals(account.getId(), accountID)).
                filter(Account::isWithdrawalAllowed).findFirst().orElse(null);
    }

    @Override
    public Account getClientAccount(String clientID, String accountID) {

        List<Account> accountList = getClientAccounts(clientID);
        return accountList.stream().filter(account -> Objects.equals(account.getId(), accountID)).findFirst().
                orElse(null);
    }
}
