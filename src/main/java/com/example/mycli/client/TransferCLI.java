package com.example.mycli.client;

import com.example.mycli.model.Account;
import com.example.mycli.repository.AccountRepositoryInterface;
import com.example.mycli.server.AccountWithdraw;
import com.example.mycli.server.TransactionTransfer;
import com.example.mycli.server.TransactionWithdraw;
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

    public void transferMoney(String clientID) {
        System.out.println("Please enter account FROM which you want transfer money");
        String accountNumberFrom = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        System.out.println("Please enter account TO which you want transfer money");
        String accountNumberTo = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        if (accountNumberFrom.isEmpty() || accountNumberTo.isEmpty()) return;
        AccountWithdraw accountWithdraw = accountListingService.getClientWithdrawAccount(clientID, accountNumberFrom);
        Account accountDeposit = accountListingService.getClientAccount(clientID, accountNumberTo);
        if (accountWithdraw != null || accountDeposit != null) {
            double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            if (amount > 0 && Objects.requireNonNull(accountWithdraw).getBalance() >= amount) {
                transactionTransfer.execute(accountWithdraw, accountDeposit, amount);
                System.out.println(String.format("%.2f", amount) + "$ transferred from " + accountNumberFrom +
                        " account to " + accountNumberTo + " account");
            } else if (amount > 0 && accountWithdraw.getBalance() < amount) {
                System.out.println("Not enough money on your account!");
            }
        } else {
            System.out.println("No withdraw account was found!");
        }
    }
}
