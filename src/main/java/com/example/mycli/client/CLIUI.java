package com.example.mycli.client;

public interface CLIUI extends CreateAccountOperationUI {
    static void greeting() {
        System.out.println("Welcome to CLI bank service!");
    }
    static void helpText() {
        String helpText = """
                Please enter operation number:
                1 - show account
                2 - create account
                3 - deposit
                4 - withdraw
                5 - transfer
                6 - this message
                7 - exit""";
        System.out.println(helpText);
    }
    static void helpTextAuth() {
        String helpText = """
                Please enter operation number:
                1 - registration
                2 - authentication
                3 - this message
                4 - exit""";
        System.out.println(helpText);
    }

    static boolean exit() {
        System.out.println("Application closed!");
        return false;
    }

    static void authentication() {
        System.out.println("Please choose authentication operation");
    }
}
