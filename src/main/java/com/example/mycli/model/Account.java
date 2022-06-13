package com.example.mycli.model;

import com.example.mycli.server.AccountType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Sequence_gen", allocationSize = 1)
    private Long main_id;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String id;
    private String clientID;
    private Double balance;
    private Boolean withdrawalAllowed;

    public Account(AccountType accountType, String id, String clientID, Double balance, Boolean withdrawalAllowed) {
        this.accountType = accountType;
        this.id = id;
        this.clientID = clientID;
        this.balance = balance;
        this.withdrawalAllowed = withdrawalAllowed;
    }

    public boolean isWithdrawalAllowed() {
        return withdrawalAllowed;
    }

    @Override
    public String toString() {
        return "Account{type=" + accountType + ", id=" +  id + ", clientID=" + clientID + ", balance=" + balance + "}";
    }

}
