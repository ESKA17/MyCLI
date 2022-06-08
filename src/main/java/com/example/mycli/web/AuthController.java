package com.example.mycli.web;



import com.example.mycli.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestParam String login, @RequestParam String password) {
        UserEntity u = new UserEntity();
        u.setPassword(password);
        u.setLogin(login);
        userService.saveUser(u);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestParam String login, @RequestParam String password) {
        UserEntity userEntity = userService.findByLoginAndPassword(login, password);
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}

