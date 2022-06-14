package com.example.mycli.model;

import com.example.mycli.server.AccountType;
import lombok.*;
import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    private @Id long transactionID;
    private String operation;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String id;
    private String clientID;
    private Double amount;

    @Override
    public String toString() {
        return "Account{type=" + accountType + ", id=" +  id + ", clientID=" + clientID + ", amount=" + amount + "}";
    }
}
