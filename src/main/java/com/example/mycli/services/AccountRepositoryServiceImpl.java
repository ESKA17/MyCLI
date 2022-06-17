package com.example.mycli.services;

import com.example.mycli.model.Account;
import com.example.mycli.repository.AccountRepository;
import com.example.mycli.server.AccountType;
import com.example.mycli.server.AccountWithdraw;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountRepositoryServiceImpl implements AccountRepositoryService {
    private final AccountRepository accountRepository;
    @Override
    public List<Account> getClientAccounts(long clientID) {
        return accountRepository.findAccountByUserEntity_Id(clientID);
    }

    @Override
    public void createNewAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<Account> getClientAccountsByType(long clientID, AccountType accountType) {
        return accountRepository.findAccountByUserEntity_IdAndAccountType(clientID, accountType);
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(long clientID, String accountID) {
        Account out = accountRepository.findAccountByUserEntity_IdAndId(clientID,
                accountID).orElse(null);
        if (out != null) {
            if (out.getWithdrawalAllowed()) return (AccountWithdraw) out;
        }
        return null;
    }

    @Override
    public Account getClientAccount(long clientID, String accountID) {
        return accountRepository.findAccountByUserEntity_IdAndId(clientID, accountID).orElse(null);
    }

    @Override
    public Account getAccount(String accountID) {
        return accountRepository.findById(accountID).orElse(null);
    }
}
