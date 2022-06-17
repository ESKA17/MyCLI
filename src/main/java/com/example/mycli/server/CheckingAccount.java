package com.example.mycli.server;

import com.example.mycli.model.UserEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class CheckingAccount extends AccountWithdraw{
    public CheckingAccount(String accountID, double balance, UserEntity userEntity) {
        super(AccountType.CHECKING, accountID, balance, userEntity);
    }
}
