package com.example.mycli.server;

public class BankCore {
    private static final long id = 1;
    private static long lastAccountNumber = 1;
    private final AccountCreationService accountCreationService;

    public BankCore(AccountCreationService accountCreationService) {
        this.accountCreationService = accountCreationService;
    }
    public void createNewAccount(AccountType accountType, String clientID) {
        this.accountCreationService.create(accountType, id, clientID, lastAccountNumber);
        incrementLasAccountNumber();
    }

    private void incrementLasAccountNumber() {
        lastAccountNumber++;
    }
}
