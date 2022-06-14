package com.example.mycli.model;

import com.example.mycli.server.AccountType;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Scope("prototype")
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
