package com.example.mycli;
import java.util.Scanner;

public class MyCLI implements CLIUI {

    private static Scanner scanner;

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

    public AccountType requestAccountType() {
        return null;
    }

    public static void main(String[] args) {
        CLIUI.greeting();
        CLIUI.helpText();
        new MyCLI();
        boolean ask = true;
        String input;
        while (ask) {
            input = scanner.nextLine();
            switch (input) {
                case "1" -> System.out.println("HERE!");
                case "6" -> CLIUI.helpText();
                case "7" -> ask = CLIUI.exit();
            }
        }
    }
}


