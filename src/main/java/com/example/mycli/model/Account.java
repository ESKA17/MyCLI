package com.example.mycli.model;

import com.example.mycli.server.AccountType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private @Id String id;
    private Double balance;
    private Boolean withdrawalAllowed;
    @ManyToOne
    private UserEntity userEntity;


    @Override
    public String toString() {
        return "Account{type=" + accountType + ", id=" +  id + ", clientID=" + userEntity.getId() +
                ", balance=" + balance + "}";
    }

}
