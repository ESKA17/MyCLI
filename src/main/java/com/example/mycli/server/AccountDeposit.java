package com.example.mycli.server;

import com.example.mycli.model.Account;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountDeposit extends Account {

    public AccountDeposit(AccountType accountType, String accountID, String clientID, double balance) {
        super(accountType, accountID, clientID, balance, false);
    }
}
