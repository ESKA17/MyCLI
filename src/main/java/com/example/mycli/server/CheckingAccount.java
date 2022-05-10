package com.example.mycli.server;

public class CheckingAccount extends AccountWithdraw{

    private final static AccountType accountType = AccountType.CHECKING;

    public CheckingAccount(String id, String clientID, double balance) {
        super(accountType, id, clientID, balance);
    }
}
