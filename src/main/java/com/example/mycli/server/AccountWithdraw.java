package com.example.mycli.server;

import com.example.mycli.model.Account;
import com.example.mycli.model.UserEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountWithdraw extends Account {

    public AccountWithdraw(AccountType accountType, String accountID, double balance, UserEntity userEntity) {
        super(accountType, accountID, balance, true, userEntity);
    }

}
