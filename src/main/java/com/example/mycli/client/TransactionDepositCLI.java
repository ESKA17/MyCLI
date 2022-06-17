package com.example.mycli.client;

import com.example.mycli.model.Account;
import com.example.mycli.server.TransactionDeposit;
import com.example.mycli.services.AccountListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionDepositCLI {

    private final TransactionDeposit transactionDeposit;
    private final WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    private final AccountListingService accountListingService;

    public void depositMoney(long clientID) {
        String accountNumber = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        if (accountNumber.isEmpty()) return;
        Account account = accountListingService.getClientAccount(clientID, accountNumber);
        if (account != null) {
            double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            if (amount > 0) {
                transactionDeposit.execute(account, amount);
                System.out.println(String.format("%.2f", amount) + "$ transferred to " + accountNumber + " account");
            }
        } else {
            System.out.println("No account was found!");
        }
    }
}
