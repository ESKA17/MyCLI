package com.example.mycli;

public class AccountDeposit extends Account{

    public AccountDeposit(AccountType accountType, String accountID, String clientID, double balance) {
        super(accountType, accountID, clientID, balance, false);
    }
}
