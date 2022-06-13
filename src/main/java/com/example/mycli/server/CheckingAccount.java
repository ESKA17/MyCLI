package com.example.mycli.server;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class CheckingAccount extends AccountWithdraw{
    public CheckingAccount(String id, String clientID, double balance) {
        super(AccountType.CHECKING, id, clientID, balance);
    }
}
