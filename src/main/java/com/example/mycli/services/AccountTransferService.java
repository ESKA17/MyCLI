package com.example.mycli.services;

import com.example.mycli.model.Account;
import com.example.mycli.server.AccountWithdraw;

public interface AccountTransferService {
    void transfer(double amount, AccountWithdraw accountWithdraw, Account accountDeposit);
}
