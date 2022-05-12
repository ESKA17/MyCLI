package com.example.mycli.server;

import com.example.mycli.dao.TransactionDAO;
import com.example.mycli.services.AccountWithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionWithdraw {

    private AccountWithdrawService accountWithdrawService;
    private TransactionDAO transactionDAO;

    public void execute(AccountWithdraw accountWithdraw, double amount) {
        accountWithdrawService.withdraw(amount, accountWithdraw);
        transactionDAO.addTransaction(new Transaction(accountWithdraw.getAccountType(), accountWithdraw.getId(),
                accountWithdraw.getClientID(), amount));
    }

}
