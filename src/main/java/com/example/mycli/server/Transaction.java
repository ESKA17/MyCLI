package com.example.mycli.server;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema
public class Transaction {


    private @Id long transactionID;
    private String operation;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String id;
    private String clientID;
    private double amount;

    @Override
    public String toString() {
        return "Account{type=" + accountType + ", id=" +  id + ", clientID=" + clientID + ", amount=" + amount + "}";
    }
}
