package com.example.mycli.client;
import com.example.mycli.server.*;

import java.util.Scanner;

public class MyCLI implements CLIUI {

    public static Scanner scanner;

    public MyCLI() {
        scanner = new Scanner(System.in);
    }

    public MyCLI(Scanner scanner) {
        MyCLI.scanner = scanner;
    }

    public double requestClientAmount() {
        return 0;
    }

    public String requestClientAccountNumber() {
        return null;
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


