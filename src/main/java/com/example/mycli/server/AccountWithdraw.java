package com.example.mycli.server;

import com.example.mycli.model.Account;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountWithdraw extends Account {

    public AccountWithdraw(AccountType accountType, String id, String clientID, double balance) {
        super(accountType, id, clientID, balance, true);
    }

}
