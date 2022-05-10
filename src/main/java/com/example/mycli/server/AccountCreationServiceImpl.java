package com.example.mycli.server;
import com.example.mycli.dao.AccountDAO;
import com.example.mycli.server.*;

public class AccountCreationServiceImpl implements AccountCreationService {
    private final com.example.mycli.dao.AccountDAO AccountDAO;

    public AccountCreationServiceImpl(AccountDAO accountDAO) {
        AccountDAO = accountDAO;
    }
    @Override
    public void create(AccountType accountType, long bankID, String clientID, long accountID) {
        String accountNumber = String.format("%03d%06d", bankID, accountID);
        Account account;
        if (accountType == AccountType.FIXED) {
            account = new AccountDeposit(accountType, accountNumber, clientID, 0.0);
        } else {
            account = new AccountWithdraw(accountType, accountNumber, clientID, 0.0);
        }
        AccountDAO.createNewAccount(account);
    }
}
