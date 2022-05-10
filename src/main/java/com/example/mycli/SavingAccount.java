package com.example.mycli;

public class SavingAccount extends AccountWithdraw{
    private final static AccountType accountType = AccountType.SAVING;

    public SavingAccount(String accountID, String clientID, double balance) {
        super(accountType, accountID, clientID, balance);
    }
}
