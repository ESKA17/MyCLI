package com.example.mycli;

import java.util.List;

public class AccountListingSeerviceImpl implements AccountListingService {
    private AccountDAO accountDAO;

    @Override
    public Account getClientAccount() {
        return null;
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        return null;
    }

    @Override
    public List<Account> getClientAccounts(String clientID) {
        return null;
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType) {
        return null;
    }
}
