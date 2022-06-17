package com.example.mycli.services;

import com.example.mycli.server.AccountType;

public interface AccountCreationService {
    void create(AccountType accountType, long bankID, long clientID, long accountID);
}
