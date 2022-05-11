package com.example.mycli;

import com.example.mycli.client.MyCLI;
import com.example.mycli.server.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class MyCliApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("props.xml");
        SpringApplication.run(MyCliApplication.class, args);
        context.getBean(MyCLI.class);
        CLIUI.greeting();
        CLIUI.helpText();
        AccountBasicCLI accountBasicCLI = context.getBean(AccountBasicCLI.class);
        boolean work = true;
        String clientID = "1";
        String input;
        while (work) {
            input = MyCLI.scanner.nextLine();
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

}
