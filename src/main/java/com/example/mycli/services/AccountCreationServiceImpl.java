package com.example.mycli.services;
import com.example.mycli.model.Account;
import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.server.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountCreationServiceImpl implements AccountCreationService {
    private final AccountRepositoryService accountDAO;
    private final UserEntityRepository userEntityRepository;
    @Override
    public void create(AccountType accountType, long bankID, long clientID, long accountID) {
        String accountNumber = String.format("%03d%06d", bankID, accountID);
        UserEntity user = userEntityRepository.findById(clientID);
        Account account = null;
        if (accountType == AccountType.FIXED) {
            account = new FixedAccount(accountNumber, 0.0, user);
        } else if (accountType == AccountType.CHECKING){
            account = new CheckingAccount(accountNumber, 0.0, user);
        } else if (accountType == AccountType.SAVING) {
            account = new SavingAccount(accountNumber, 0.0, user);
        }
        accountDAO.createNewAccount(account);
    }
}
