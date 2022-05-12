package com.example.mycli.server;

import com.example.mycli.dao.TransactionDAO;
import com.example.mycli.services.AccountDepositService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionDeposit {
    private AccountDepositService accountDepositService;
    private TransactionDAO transactionDAO;

    public void execute(Account account, double amount) {
        accountDepositService.deposit(amount, account);
        transactionDAO.addTransaction(new Transaction(account.getAccountType(), account.getId(),
                account.getClientID(), amount));
    }

}
