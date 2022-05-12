package com.example.mycli.services;

import com.example.mycli.server.AccountType;
import org.springframework.stereotype.Service;

@Service
public interface AccountCreationService {
    void create(AccountType accountType, long bankID, String clientID, long accountID);
}
