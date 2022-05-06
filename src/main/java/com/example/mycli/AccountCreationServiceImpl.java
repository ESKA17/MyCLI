package com.example.mycli;
public class AccountCreationServiceImpl implements AccountCreationService{
    private final AccountDAO accountDAO;

    public AccountCreationServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void create(AccountType accountType, long bankID, String clientID, long accountID) {
        Me
    }
}
