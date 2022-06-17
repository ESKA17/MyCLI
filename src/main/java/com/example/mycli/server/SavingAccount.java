package com.example.mycli.server;

import com.example.mycli.model.UserEntity;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class SavingAccount extends AccountWithdraw {
    public SavingAccount(String accountID, double balance, UserEntity user) {
        super(AccountType.SAVING, accountID, balance, user);
    }
}
