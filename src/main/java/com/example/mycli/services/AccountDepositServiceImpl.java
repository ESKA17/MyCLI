package com.example.mycli.services;

import com.example.mycli.repository.AccountDAO;
import com.example.mycli.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class AccountDepositServiceImpl implements AccountDepositService{

    private AccountDAO accountDAO;
    @Override
    public void deposit(double amount, Account account) {
        BigDecimal balance = BigDecimal.valueOf(account.getBalance());
        BigDecimal amountBD = new BigDecimal(amount);
        account.setBalance(balance.add(amountBD).doubleValue());
        accountDAO.updateAccount(account);
    }
}
