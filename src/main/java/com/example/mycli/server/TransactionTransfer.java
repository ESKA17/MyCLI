package com.example.mycli.server;

import com.example.mycli.model.Account;
import com.example.mycli.services.TransactionService;
import com.example.mycli.services.AccountTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TransactionTransfer {

    private final AccountTransferService accountTransferService;
    private final TransactionService transactionService;

    public void execute(AccountWithdraw accountWithdraw, Account accountDeposit, double amount) {
        accountTransferService.transfer(amount, accountWithdraw, accountDeposit);
        transactionService.addTransaction("Transfer", accountWithdraw.getAccountType(),
                accountWithdraw.getId(), accountWithdraw.getUserEntity().getId(), amount);
    }

}
