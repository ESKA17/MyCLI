package com.example.mycli.server;


import com.example.mycli.services.AccountWithdrawService;
import com.example.mycli.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TransactionWithdraw {

    private final AccountWithdrawService accountWithdrawService;
    private final TransactionService transactionService;

    public void execute(AccountWithdraw accountWithdraw, double amount) {
        accountWithdrawService.withdraw(amount, accountWithdraw);
        transactionService.addTransaction("Withdraw", accountWithdraw.getAccountType(),
                accountWithdraw.getId(), accountWithdraw.getUserEntity().getId(), amount);
    }

}
