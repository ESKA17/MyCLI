package com.example.mycli;
public class BankCore {
    private static long id = 1;
    private static long lastAccountNumber = 1;
    private AccountCreationService accountCreationService;

    public BankCore(AccountCreationService accountCreationService) {

    }
    public void createNewAccount(AccountType accountType, String clientID) {
        this.accountCreationService.create(accountType, id, clientID, lastAccountNumber);
        incrementLasAccountNumber();
    }

    private void incrementLasAccountNumber() {
        lastAccountNumber++;
    }
}
