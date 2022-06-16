package com.example.mycli.client;

import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.web.AuthResponse;
import com.example.mycli.web.JwtProvider;
import com.example.mycli.web.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@RequiredArgsConstructor
@Service
public class AccountAuthenticationCLI {
    private final MyCLI myCLI;
    private final UserEntityRepository userEntityRepository;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public void registerAccountRequest() {
        System.out.println("Please enter your login");
        String login = myCLI.scanner.nextLine();
        UserEntity user = userEntityRepository.findByLogin(login);
        if (user == null) {
            System.out.println("Login was not found");
        }
        else {
            System.out.println("Please enter your password");
            boolean passwordMatch = passwordEncoder.matches(myCLI.scanner.nextLine(), user.getPassword());

        }
    }
}
