package com.example.mycli.server;

import com.example.mycli.repository.TransactionInterface;
import com.example.mycli.services.AccountWithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionWithdraw {

    private final AccountWithdrawService accountWithdrawService;
    private final TransactionInterface transactionInterface;

    public void execute(AccountWithdraw accountWithdraw, double amount) {
        accountWithdrawService.withdraw(amount, accountWithdraw);
        transactionInterface.addTransaction("Withdraw", accountWithdraw.getAccountType(),
                accountWithdraw.getId(), accountWithdraw.getClientID(), amount);
    }

}
