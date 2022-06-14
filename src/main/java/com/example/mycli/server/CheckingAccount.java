package com.example.mycli.server;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class CheckingAccount extends AccountWithdraw{
    public CheckingAccount(String accountID, String clientID, double balance) {
        super(AccountType.CHECKING, accountID, clientID, balance);
    }
}
