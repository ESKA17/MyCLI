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
        AccountWithdraw accountWithdraw = accountListingService.getClientWithdrawAccount(clientID, accountNumber);
        if (accountWithdraw != null) {
            double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            if (amount > 0 && accountWithdraw.getBalance() > amount) {
                transactionWithdraw.execute(accountWithdraw,amount);
            } else {
                System.out.println("Not");
            }
        }
    }
}
