package com.example.mycli.client;
import com.example.mycli.server.*;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class MyCLI implements CLIUI, WithdrawDepositOperationCLIUI {

    public Scanner scanner;

    public MyCLI() {
        scanner = new Scanner(System.in);
    }

    public MyCLI(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public double requestClientAmount() {
        System.out.println("Type amount of money:");
        String input = scanner.nextLine();
        try {
            double amount = Double.parseDouble(input);
            if (amount > 0) {
                return amount;
            } else {
                System.out.println("Amount of money should be greater than zero!");
                return 0.0;
            }
        } catch (Exception e) {
            System.out.println("Incorrect input!");
            return 0.0;
        }
    }

    @Override
    public String requestClientAccountNumber() {
        System.out.println("Type account ID:");
        String input = scanner.nextLine();
        try {
            if (Integer.parseInt(input) >= 1000001) {
                return input;
            } else {
                System.out.println("No account was found!");
                return "";
            }
        } catch (Exception e) {
            System.out.println("Check account number!");
            return "";
        }

    }

    @Override
    public AccountType requestAccountType() {
        String accountTypes = """
                Choose account type:
                [CHECKING, SAVING, FIXED]""";
        System.out.println(accountTypes);
        String input = scanner.nextLine();
        switch (input) {
            case "FIXED" -> {
                CLIUI.accountCreationDisclaimer();
                return AccountType.FIXED;
            }
            case "CHECKING" -> {
                CLIUI.accountCreationDisclaimer();
                return AccountType.CHECKING;
            }
            case "SAVING" -> {
                CLIUI.accountCreationDisclaimer();
                return AccountType.SAVING;
            }
            default -> {
                System.out.println("Wrong account type! Try again!");
                return null;
            }
        }
    }
}


