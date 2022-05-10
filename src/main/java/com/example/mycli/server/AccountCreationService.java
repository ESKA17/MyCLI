package com.example.mycli.server;

public interface AccountCreationService {
    void create(AccountType accountType, long bankID, String clientID, long accountID);
}
