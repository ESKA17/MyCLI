package com.example.mycli;

public class FixedAccount extends AccountDeposit{
    private final static AccountType accountType = AccountType.FIXED;

    public FixedAccount(String accountID, String clientID, double balance) {
        super(accountType, accountID, clientID, balance);
    }
}
