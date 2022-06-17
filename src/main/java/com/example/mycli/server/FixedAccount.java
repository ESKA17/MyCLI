package com.example.mycli.server;

import com.example.mycli.model.UserEntity;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class FixedAccount extends AccountDeposit{

    public FixedAccount(String accountID, double balance, UserEntity user) {
        super(AccountType.FIXED, accountID, balance, user);
    }
}
