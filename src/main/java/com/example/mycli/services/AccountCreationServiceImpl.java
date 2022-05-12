package com.example.mycli.services;
import com.example.mycli.dao.AccountDAO;
import com.example.mycli.server.Account;
import com.example.mycli.server.AccountDeposit;
import com.example.mycli.server.AccountType;
import com.example.mycli.server.AccountWithdraw;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class AccountCreationServiceImpl implements AccountCreationService {
    private final com.example.mycli.dao.AccountDAO accountDAO;
    @Override
    public void create(AccountType accountType, long bankID, String clientID, long accountID) {
        String accountNumber = String.format("%03d%06d", bankID, accountID);
        Account account;
        if (accountType == AccountType.FIXED) {
            account = new AccountDeposit(accountType, accountNumber, clientID, 0.0);
        } else {
            account = new AccountWithdraw(accountType, accountNumber, clientID, 0.0);
        }
        accountDAO.createNewAccount(account);
    }
}
