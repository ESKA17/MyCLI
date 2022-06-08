package com.example.mycli.services;

import com.example.mycli.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountDepositService {

    void deposit(double amount, Account account);
}
