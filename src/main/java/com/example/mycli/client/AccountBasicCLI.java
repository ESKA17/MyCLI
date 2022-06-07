package com.example.mycli.client;

import com.example.mycli.server.BankCore;
import com.example.mycli.services.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountBasicCLI {
    private final CreateAccountOperationUI createAccountOperationUI;
    private final AccountListingService accountListingService;
    private final BankCore bankCore;

    public void createAccountRequest(String clientID) {
        String accountType = createAccountOperationUI.requestAccountType();
        if (accountType != null) {
            bankCore.createNewAccount(accountType, clientID);
            System.out.println("Account was created");
        } else {
            System.out.println("Account was not created");
        }
    }
    public void getAccounts(String clientID) {
        System.out.println(accountListingService.getClientAccounts(clientID));
    }
}
