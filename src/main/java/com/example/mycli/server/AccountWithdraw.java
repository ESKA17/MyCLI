package com.example.mycli.server;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
public class AccountWithdraw extends Account {

    public AccountWithdraw(AccountType accountType, String id, String clientID, double balance) {
        super(accountType, id, clientID, balance, true);
    }

}
