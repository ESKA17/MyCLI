package com.example.mycli;

import com.example.mycli.client.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
@SpringBootApplication
public class MyCliApplication implements CommandLineRunner {
    private final ApplicationContext context;
    public long clientID;

    public MyCliApplication(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args)  {
        SpringApplication.run(MyCliApplication.class);
    }
    @Override
    public void run(String... arg0) {
        MyCLI myCLI = context.getBean(MyCLI.class);
        AccountBasicCLI accountBasicCLI = context.getBean(AccountBasicCLI.class);
        TransactionDepositCLI transactionDepositCLI = context.getBean(TransactionDepositCLI.class);
        TransactionWithdrawCLI transactionWithdrawCLI = context.getBean(TransactionWithdrawCLI.class);
        TransferCLI transferCLI = context.getBean(TransferCLI.class);
        AccountRegistrationCLI accountRegistrationCLI = context.getBean(AccountRegistrationCLI.class);
        AccountAuthenticationCLI accountAuthenticationCLI = context.getBean(AccountAuthenticationCLI.class);

        CLIUI.greeting();
        CLIUI.authentication();
        CLIUI.helpTextAuth();
        boolean regAuth = true;
        while (regAuth) {
            switch (myCLI.scanner.nextLine()) {
                case "1" -> accountRegistrationCLI.registerAccountRequest();
                case "2" -> {
                    clientID = accountAuthenticationCLI.authenticateAccount();
                    if (clientID != 0) regAuth = false;
                }
                case "3" -> CLIUI.helpTextAuth();
                case "4" -> regAuth = CLIUI.exit();
                default -> System.out.println("Wrong input!");
            }
        }
        CLIUI.helpText();
        boolean work = true;
        while (work) {
            switch (myCLI.scanner.nextLine()) {
                case "1" -> accountBasicCLI.getAccounts(clientID);
                case "2" -> accountBasicCLI.createAccountRequest(clientID);
                case "3" -> transactionDepositCLI.depositMoney(clientID);
                case "4" -> transactionWithdrawCLI.withdrawMoney(clientID);
                case "5" -> transferCLI.transferMoney(clientID);
                case "6" -> CLIUI.helpText();
                case "7" -> work = CLIUI.exit();
                default -> System.out.println("Wrong input!");
            }
        }
        myCLI.scanner.close();
    }
}
