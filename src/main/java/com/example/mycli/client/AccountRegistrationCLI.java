package com.example.mycli.client;

import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.AccountRepository;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.server.BankCore;
import com.example.mycli.services.AccountListingService;
import com.example.mycli.web.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountRegistrationCLI {
    private final MyCLI myCLI;
    private final UserEntityRepository userEntityRepository;
    private final UserService userService;

    public void registerAccountRequest() {
        System.out.println("Please enter your login");
        String login = myCLI.scanner.nextLine();
        if (userEntityRepository.findByLogin(login) != null) {
            System.out.println("Login is already taken");
        }
        else {
            System.out.println("Please enter your password");
            String password = myCLI.scanner.nextLine();
            UserEntity u = new UserEntity();
            u.setPassword(password);
            u.setLogin(login);
            userService.saveUser(u);
            System.out.println("User was successfully registered");
        }
    }
}
