package com.example.mycli;
public interface AccountCreationService {
    void create(AccountType accountType, long bankID, String clientID, long accountID);
}
