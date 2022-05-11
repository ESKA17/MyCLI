package com.example.mycli.server;

public class AccountBasicCLI {
    private final CreateAccountOperationUI createAccountOperationUI;
    private final AccountListingService accountListingService;
    private final BankCore bankCore;

    public AccountBasicCLI(CreateAccountOperationUI createAccountOperationUI, BankCore bankCore,
                           AccountListingService accountListingService) {
        this.accountListingService = accountListingService;
        this.bankCore = bankCore;
        this.createAccountOperationUI = createAccountOperationUI;
    }
    public void createAccountRequest(String clientID) {
        AccountType accountType = createAccountOperationUI.requestAccountType();
        if (accountType != null) bankCore.createNewAccount(accountType, clientID);
    }
    public void getAccounts(String clientID) {
        System.out.println(accountListingService.getClientAccounts(clientID));
    }
}
