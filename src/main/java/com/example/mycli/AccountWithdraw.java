package com.example.mycli;
public class AccountWithdraw extends Account{

    public AccountWithdraw(AccountType accountType, String id, String clientID, double balance, boolean withdrawalAllowed) {
        super(accountType, id, clientID, balance, withdrawalAllowed);
    }
}
