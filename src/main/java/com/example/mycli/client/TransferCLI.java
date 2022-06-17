package com.example.mycli.client;

import com.example.mycli.model.Account;
import com.example.mycli.server.AccountWithdraw;
import com.example.mycli.server.TransactionTransfer;
import com.example.mycli.services.AccountListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TransferCLI {
    private final TransactionTransfer transactionTransfer;
    private final WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    private final AccountListingService accountListingService;

    public void transferMoney(long clientID) {
        System.out.println("Please enter account FROM which you want transfer money");
        String accountNumberFrom = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        if (accountNumberFrom.isEmpty() || accountListingService.getClientWithdrawAccount(clientID, accountNumberFrom)
                == null) {
            System.out.println("No withdraw account was found");
            return;
        }
        System.out.println("Please enter account TO which you want transfer money");
        String accountNumberTo = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        if (accountNumberTo.isEmpty() || accountListingService.getAccount(accountNumberTo) == null) {
            System.out.println("No account was found");
            return;
        }
        AccountWithdraw accountWithdraw = accountListingService.getClientWithdrawAccount(clientID, accountNumberFrom);
        Account accountDeposit = accountListingService.getAccount(accountNumberTo);
        double amount = withdrawDepositOperationCLIUI.requestClientAmount();
        if (amount > 0 && accountWithdraw.getBalance() >= amount) {
            transactionTransfer.execute(accountWithdraw, accountDeposit, amount);
            System.out.println(String.format("%.2f", amount) + "$ transferred from " + accountNumberFrom +
                    " account to " + accountNumberTo + " account");
        } else if (amount > 0 && accountWithdraw.getBalance() < amount) {
            System.out.println("Not enough money on your account!");
        }
    }
}
