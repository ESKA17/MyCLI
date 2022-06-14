package com.example.mycli.model;

import com.example.mycli.server.AccountType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private @Id String id;
    private String clientID;
    private Double balance;
    private Boolean withdrawalAllowed;

    @Override
    public String toString() {
        return "Account{type=" + accountType + ", id=" +  id + ", clientID=" + clientID + ", balance=" + balance + "}";
    }

}
