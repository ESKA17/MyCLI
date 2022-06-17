package com.example.mycli.services;

import com.example.mycli.model.Account;
import com.example.mycli.server.AccountType;
import com.example.mycli.server.AccountWithdraw;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountListingServiceImpl implements AccountListingService {

    private final AccountRepositoryService accountDAO;

    @Override
    public Account getClientAccount(long clientID, String accountID) {
        return accountDAO.getClientAccount(clientID, accountID);
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(long clientID, String accountID) {
        return accountDAO.getClientWithdrawAccount(clientID, accountID);
    }

    @Override
    public List<Account> getClientAccounts(long clientID) {
        return accountDAO.getClientAccounts(clientID);
    }

    @Override
    public Account getAccount(String accountID) {
        return accountDAO.getAccount(accountID);
    }
}
