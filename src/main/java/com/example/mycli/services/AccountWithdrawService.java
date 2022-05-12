package com.example.mycli.services;

import com.example.mycli.server.AccountWithdraw;
import org.springframework.stereotype.Service;

@Service
public interface AccountWithdrawService {
    void withdraw(double amount, AccountWithdraw accountWithdraw);
}
