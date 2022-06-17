package com.example.mycli.client;

import com.example.mycli.server.AccountWithdraw;
import com.example.mycli.server.TransactionWithdraw;
import com.example.mycli.services.AccountListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionWithdrawCLI {
    private final TransactionWithdraw transactionWithdraw;
    private final WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    private final AccountListingService accountListingService;

    public void withdrawMoney(long clientID) {
        String accountNumber = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        if (accountNumber.isEmpty()) return;
        AccountWithdraw accountWithdraw = accountListingService.getClientWithdrawAccount(clientID, accountNumber);
        if (accountWithdraw != null) {
            double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            if (amount > 0 && accountWithdraw.getBalance() >= amount) {
                transactionWithdraw.execute(accountWithdraw, amount);
                System.out.println(String.format("%.2f", amount) + "$ transferred from " + accountNumber + " account");
            } else if (amount > 0 && accountWithdraw.getBalance() < amount) {
                System.out.println("Not enough money on your account!");
            }
        } else {
            System.out.println("No withdraw account was found!");
        }
    }
}
