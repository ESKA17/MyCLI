package com.example.mycli;
public class AccountBasicCLI {
    private CreateAccountOperationUI createAccountOperationUI;
    private BankCore bankCore;
    private AccountListingService accountListingService;

    public AccountBasicCLI(CreateAccountOperationUI createAccountOperationUI, BankCore bankCore, AccountListingService accountListingService) {
        this.createAccountOperationUI = createAccountOperationUI;
        this.bankCore = bankCore;
        this.accountListingService = accountListingService;
    }
    public void createAccountRequest(String clientID) {}

    public void getAccounts(String clientID) {}
}
