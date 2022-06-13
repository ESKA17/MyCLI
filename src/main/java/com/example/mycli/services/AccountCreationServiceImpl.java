package com.example.mycli.services;
import com.example.mycli.model.Account;
import com.example.mycli.repository.AccountRepositoryInterface;
import com.example.mycli.server.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountCreationServiceImpl implements AccountCreationService {
    private final AccountRepositoryInterface accountDAO;
    @Override
    public void create(AccountType accountType, long bankID, String clientID, long accountID) {
        String accountNumber = String.format("%03d%06d", bankID, accountID);
        Account account = null;
        if (accountType == AccountType.FIXED) {
            account = new FixedAccount(accountNumber, clientID, 0.0);
        } else if (accountType == AccountType.CHECKING){
            account = new CheckingAccount(accountNumber, clientID, 0.0);
        } else if (accountType == AccountType.SAVING) {
            account = new SavingAccount(accountNumber, clientID,0.0);            
        }
        accountDAO.createNewAccount(account);
    }
}
