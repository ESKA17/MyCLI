package com.example.mycli.client;
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
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Wrong input!");
            return 0.0;
        }
    }

    @Override
    public String requestClientAccountNumber() {
        System.out.println("Type account ID:");
        try {
            String input = scanner.nextLine();
            if (Integer.parseInt(input) >= 1000001) {
                return input;
            } else {
                System.out.println("Check account number!");
                return "";
            }
        } catch (Exception e) {
            System.out.println("Wrong input!");
            return "";
        }

    }

    @Override
    public String requestAccountType() {
        String accountTypes = """
                Choose account type:
                [CHECKING, SAVING, FIXED]""";
        System.out.println(accountTypes);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("checking") || input.equalsIgnoreCase("fixed") ||
                input.equalsIgnoreCase("saving")) {
            return input;
        } else {
            System.out.println("Wrong input");
            return null;
        }
    }


}


