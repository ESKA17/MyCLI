package com.example.mycli.web;



import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor

public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final UserEntityRepository userEntityRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegRequest registrationRequest) {
        UserEntity userEntity = userService.findByLogin(registrationRequest.getLogin());
        if (userEntity == null) {
            UserEntity u = new UserEntity();
            u.setPassword(registrationRequest.getPassword());
            u.setLogin(registrationRequest.getLogin());
            userService.saveUser(u);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login is already taken");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody @Valid AuthRequest authRequest,HttpServletResponse httpServletResponse) {
        UserEntity userEntity = userService.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
        if (userEntity != null) {
            String token = jwtProvider.generateToken(userEntity.getLogin());
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            httpServletResponse.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully authenticated, your token: " +
            new AuthResponse(token).getToken());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check your login and/or password");
        }
    }
    @GetMapping("/users")
    public List<UserEntity> users() {
        return userEntityRepository.findAll();
    }
}

