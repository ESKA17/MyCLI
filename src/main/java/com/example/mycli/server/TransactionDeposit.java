package com.example.mycli.server;

import com.example.mycli.model.Account;
import com.example.mycli.services.AccountDepositService;
import com.example.mycli.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TransactionDeposit {
    private final AccountDepositService accountDepositService;
    private final TransactionService transactionService;

    public void execute(Account account, double amount) {
        accountDepositService.deposit(amount, account);
        transactionService.addTransaction("Deposit", account.getAccountType(), account.getId(),
                account.getUserEntity().getId(), amount);
    }

}
