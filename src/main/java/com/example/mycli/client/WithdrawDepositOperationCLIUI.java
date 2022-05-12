package com.example.mycli.client;

import org.springframework.stereotype.Service;

@Service
public interface WithdrawDepositOperationCLIUI {

    double requestClientAmount();

    String requestClientAccountNumber();

}
