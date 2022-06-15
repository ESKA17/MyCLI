package com.example.mycli.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransferRequest {
    private String destination_account_id;
    private double amount;
}
