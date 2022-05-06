package com.example.mycli;
import java.util.List;

public interface AccountListingService {
    Account getClientAccount();

    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);

    List<Account> getClientAccounts(String clientID);

    List<Account> getClientAccountsByType(String clientID, AccountType accountType);
}
