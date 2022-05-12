package com.example.mycli.services;

import com.example.mycli.server.Account;
import com.example.mycli.server.AccountType;
import com.example.mycli.server.AccountWithdraw;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountListingService {
    Account getClientAccount(String clientID, String accountID);

    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);

    List<Account> getClientAccounts(String clientID);

    List<Account> getClientAccountsByType(String clientID, AccountType accountType);
}
