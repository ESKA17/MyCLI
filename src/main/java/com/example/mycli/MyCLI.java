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

    public static void main(String[] args) {
        MyCLI myCLI = new MyCLI();
        CLIUI.greeting();
        CLIUI.helpText();
        MemoryAccountDAO memoryAccountDAO = new MemoryAccountDAO();
        BankCore bankCore = new BankCore(new AccountCreationServiceImpl(memoryAccountDAO));

        AccountBasicCLI accountBasicCLI =
                new AccountBasicCLI(myCLI, bankCore, new AccountListingServiceImpl(memoryAccountDAO));
        boolean work = true;
        String clientID = "1";
        String input;
        while (work) {
            input = scanner.nextLine();
            switch (input) {
                case "1" -> accountBasicCLI.getAccounts(clientID);
                case "2" -> {
                    accountBasicCLI.createAccountRequest(clientID);
                    CLIUI.accountCreationDisclaimer();
                }
                case "6" -> CLIUI.helpText();
                case "7" -> work = CLIUI.exit();
            }
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


