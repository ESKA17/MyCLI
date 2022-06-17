package com.example.mycli.services;

import com.example.mycli.model.Account;
import com.example.mycli.server.AccountWithdraw;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountTransferServiceImpl implements AccountTransferService{

    private final AccountWithdrawService accountWithdrawService;
    private final AccountDepositService accountDepositService;

    @Override
    public void transfer(double amount, AccountWithdraw accountWithdraw, Account accountDeposit) {
        accountWithdrawService.withdraw(amount, accountWithdraw);
        accountDepositService.deposit(amount, accountDeposit);
    }
}
