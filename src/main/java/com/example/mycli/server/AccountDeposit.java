package com.example.mycli.server;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
@NoArgsConstructor
public class AccountDeposit extends Account {

    public AccountDeposit(AccountType accountType, String accountID, String clientID, double balance) {
        super(accountType, accountID, clientID, balance, false);
    }
}
