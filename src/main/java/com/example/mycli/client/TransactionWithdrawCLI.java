package com.example.mycli.client;

import com.example.mycli.server.AccountWithdraw;
import com.example.mycli.server.TransactionWithdraw;
import com.example.mycli.services.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionWithdrawCLI {
    TransactionWithdraw transactionWithdraw;
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    AccountListingService accountListingService;

    public void withdrawMoney(String clientID) {
        String accountNumber = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        if (accountNumber.isEmpty()) return;
        AccountWithdraw accountWithdraw = accountListingService.getClientWithdrawAccount(clientID, accountNumber);
        if (accountWithdraw != null) {
            double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            if (amount > 0 && accountWithdraw.getBalance() >= amount) {
                transactionWithdraw.execute(accountWithdraw,amount);
                System.out.println(String.format("%.2f", amount) + "$ transferred from " + accountNumber + " account");
            } else if (amount > 0 && accountWithdraw.getBalance() < amount) {
                System.out.println("Not enough money on your account!");
            }
        } else {
            System.out.println("No withdraw account was found!");
        }
    }
}
