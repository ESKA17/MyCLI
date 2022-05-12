package com.example.mycli.services;

import com.example.mycli.dao.AccountDAO;
import com.example.mycli.server.AccountWithdraw;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService{

    private AccountDAO accountDAO;

    @Override
    public void withdraw(double amount, AccountWithdraw accountWithdraw) {
        BigDecimal balance = BigDecimal.valueOf(accountWithdraw.getBalance());
        BigDecimal amountBD = new BigDecimal(amount);
        accountWithdraw.setBalance(balance.subtract(amountBD).doubleValue());
        accountDAO.updateAccount(accountWithdraw);
    }
}
