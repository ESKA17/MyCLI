package com.example.mycli.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class Account {
    private AccountType accountType;
    private String id;
    private String clientID;
    private double balance;
    private boolean withdrawalAllowed;

    public boolean isWithdrawalAllowed() {
        return withdrawalAllowed;
    }

    @Override
    public String toString() {
        return "Account{type=" + accountType + ", id=" +  id + ", clientID=" + clientID + ", balance=" + balance + "}";
    }
}
