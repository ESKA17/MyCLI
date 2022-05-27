package com.example.mycli.server;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class SavingAccount extends AccountWithdraw {

    public SavingAccount(String accountID, String clientID, double balance) {
        super(AccountType.SAVING, accountID, clientID, balance);
    }
}
