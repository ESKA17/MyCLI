package com.example.mycli.services;

import com.example.mycli.server.AccountWithdraw;

public interface AccountWithdrawService {
    void withdraw(double amount, AccountWithdraw accountWithdraw);
}
