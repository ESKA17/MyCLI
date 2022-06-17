package com.example.mycli.services;

import com.example.mycli.model.Account;
import com.example.mycli.server.AccountType;
import com.example.mycli.server.AccountWithdraw;
import java.util.List;

public interface AccountListingService {
    Account getClientAccount(long clientID, String accountID);

    AccountWithdraw getClientWithdrawAccount(long clientID, String accountID);

    List<Account> getClientAccounts(long clientID);

    Account getAccount(String accountID);
}
