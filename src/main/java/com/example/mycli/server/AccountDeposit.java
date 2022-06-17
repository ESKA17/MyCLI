package com.example.mycli.server;

import com.example.mycli.model.Account;
import com.example.mycli.model.UserEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountDeposit extends Account {

    public AccountDeposit(AccountType accountType, String accountID, double balance, UserEntity userEntity) {
        super(accountType, accountID, balance, false, userEntity);
    }
}
