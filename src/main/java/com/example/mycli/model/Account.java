package com.example.mycli.model;

import com.example.mycli.server.AccountType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account {
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private @Id String id;
    private String clientID;
    private Double balance;
    private Boolean withdrawalAllowed;
    @ManyToOne
    private UserEntity userEntity;

    public Account(AccountType accountType, String id, String clientID, Double balance, Boolean withdrawalAllowed) {
        this.accountType = accountType;
        this.id = id;
        this.clientID = clientID;
        this.balance = balance;
        this.withdrawalAllowed = withdrawalAllowed;
    }

    @Override
    public String toString() {
        return "Account{type=" + accountType + ", id=" +  id + ", clientID=" + clientID + ", balance=" + balance + "}";
    }

}
