package com.example.mycli.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
public class Transaction {
    private AccountType accountType;
    private @Id String id;
    private String clientID;
    private double amount;
}
