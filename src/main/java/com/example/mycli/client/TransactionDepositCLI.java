package com.example.mycli.client;

import com.example.mycli.server.Account;
import com.example.mycli.server.AccountDeposit;
import com.example.mycli.server.TransactionDeposit;
import com.example.mycli.services.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionDepositCLI {

    private TransactionDeposit transactionDeposit;
    private WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    private AccountListingService accountListingService;

    public void depositMoney(String clientID) {
        String accountNumber = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        Account account = accountListingService.getClientAccount(clientID, accountNumber);
        if (account != null) {
            double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            if (amount > 0) {
                transactionDeposit.execute(account, amount);
                System.out.println(amount + "");
            }
        }
    }
}
