package com.example.mycli.client;

import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountAuthenticationCLI {
    private final MyCLI myCLI;
    private final UserEntityRepository userEntityRepository;

    private final PasswordEncoder passwordEncoder;

    public long authenticateAccount() {
        System.out.println("Please enter your login");
        String login = myCLI.scanner.nextLine();
        UserEntity user = userEntityRepository.findByLogin(login);
        if (user == null) {
            System.out.println("Login was not found");
        }
        else {
            System.out.println("Please enter your password");
            boolean passwordMatch = passwordEncoder.matches(myCLI.scanner.nextLine(), user.getPassword());
            if(passwordMatch) return user.getId();
            else System.out.println("Password is incorrect");
        }
        return 0;
    }
}
