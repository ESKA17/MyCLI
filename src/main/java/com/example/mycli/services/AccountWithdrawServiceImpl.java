package com.example.mycli.services;

import com.example.mycli.server.AccountWithdraw;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService{

    private final AccountRepositoryService accountDAO;

    @Override
    public void withdraw(double amount, AccountWithdraw accountWithdraw) {
        BigDecimal balance = BigDecimal.valueOf(accountWithdraw.getBalance());
        BigDecimal amountBD = new BigDecimal(amount);
        accountWithdraw.setBalance(balance.subtract(amountBD).doubleValue());
        accountDAO.updateAccount(accountWithdraw);
    }
}
