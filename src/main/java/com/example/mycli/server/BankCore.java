package com.example.mycli.server;

import com.example.mycli.repository.AccountRepository;
import com.example.mycli.services.AccountCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankCore {
    private static final long id = 1;
    private final AccountCreationService accountCreationService;
    private final AccountRepository accountRepository;


    public void createNewAccount(String accountTypeStr, long clientID) {
        AccountType accountType = null;
        switch (accountTypeStr.toUpperCase()) {
            case "FIXED" -> accountType = AccountType.FIXED;
            case "CHECKING" -> accountType = AccountType.CHECKING;
            case "SAVING" -> accountType = AccountType.SAVING;
        }
        if (accountType != null) {
            long lastAccountNumber = accountRepository.getNextSeriesId();

            this.accountCreationService.create(accountType, id, clientID, lastAccountNumber);
        }
    }

}
