package com.example.mycli.services;

import com.example.mycli.model.Account;

public interface AccountDepositService {

    void deposit(double amount, Account account);
}
