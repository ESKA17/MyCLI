package com.example.mycli.server;

import com.example.mycli.model.Account;
import com.example.mycli.repository.TransactionInterface;
import com.example.mycli.services.AccountDepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TransactionDeposit {
    private final AccountDepositService accountDepositService;
    private final TransactionInterface transactionInterface;

    public void execute(Account account, double amount) {
        accountDepositService.deposit(amount, account);
        transactionInterface.addTransaction("Deposit", account.getAccountType(), account.getId(),
                account.getClientID(), amount);
    }

}
