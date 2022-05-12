package com.example.mycli.services;

import com.example.mycli.server.Account;
import com.example.mycli.server.AccountDeposit;
import com.example.mycli.server.AccountWithdraw;
import org.springframework.stereotype.Service;

@Service
public interface AccountDepositService {

    void deposit(double amount, Account account);
}
