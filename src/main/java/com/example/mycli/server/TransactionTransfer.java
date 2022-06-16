package com.example.mycli.server;

import com.example.mycli.model.Account;
import com.example.mycli.repository.TransactionInterface;
import com.example.mycli.services.AccountDepositService;
import com.example.mycli.services.AccountTransferService;
import com.example.mycli.services.AccountWithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TransactionTransfer {

    private final AccountTransferService accountTransferService;
    private final TransactionInterface transactionInterface;

    public void execute(AccountWithdraw accountWithdraw, Account accountDeposit, double amount) {
        accountTransferService.transfer(amount, accountWithdraw, accountDeposit);
        transactionInterface.addTransaction("Transfer", accountWithdraw.getAccountType(),
                accountWithdraw.getId(), accountWithdraw.getClientID(), amount);
    }

}
