package com.example.mycli.web;



import com.example.mycli.exceptions.AuthenticationFailed;
import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private JwtProvider jwtProvider;
    private UserEntityRepository userEntityRepository;

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

    @GetMapping("/users")
    public List<UserEntity> users() {
        return userEntityRepository.findAll();

    }
}

