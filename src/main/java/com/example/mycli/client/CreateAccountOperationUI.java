package com.example.mycli.client;

import com.example.mycli.server.AccountType;
import org.springframework.stereotype.Service;

@Service
public interface CreateAccountOperationUI {
    AccountType requestAccountType();

}
