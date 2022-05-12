package com.example.mycli;

import com.example.mycli.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class MyCliApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext context;

    public static void main(String[] args)  {

        SpringApplication.run(MyCliApplication.class);
    }
    @Override
    public void run(String... arg0) {
        CLIUI.greeting();
        CLIUI.helpText();
        MyCLI myCLI = context.getBean(MyCLI.class);
        AccountBasicCLI accountBasicCLI = context.getBean(AccountBasicCLI.class);
        TransactionDepositCLI transactionDepositCLI = context.getBean(TransactionDepositCLI.class);
        TransactionWithdrawCLI transactionWithdrawCLI = context.getBean(TransactionWithdrawCLI.class);
        boolean work = true;
        String clientID = "1";
        while (work) {
            switch (myCLI.scanner.nextLine()) {
                case "1" -> accountBasicCLI.getAccounts(clientID);
                case "2" -> accountBasicCLI.createAccountRequest(clientID);
                case "3" -> transactionDepositCLI.depositMoney(clientID);
                case "4" -> transactionWithdrawCLI.withdrawMoney(clientID);
                case "6" -> CLIUI.helpText();
                case "7" -> work = CLIUI.exit();
                default -> System.out.println("Wrong input!");
            }
        }
        myCLI.scanner.close();
    }
}
