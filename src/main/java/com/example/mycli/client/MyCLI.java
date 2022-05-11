package com.example.mycli.client;
import com.example.mycli.dao.MemoryAccountDAO;
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
            case "FIXED":
                return AccountType.FIXED;
            case "CHECKING":
                return AccountType.CHECKING;
            case "SAVING":
                return AccountType.SAVING;
            default:
                System.out.println("Wrong account type! Try again!");
                CLIUI.helpText();
        }
        return null;
    }
}


