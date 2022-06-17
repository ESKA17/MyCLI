package com.example.mycli.services;

import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.web.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountRegistrationServiceImpl implements AccountRegistrationService{

    private final UserEntityRepository userEntityRepository;
    private final UserService userService;

    @Override
    public String registerAccount(String login, String password) {
        if (userEntityRepository.findByLogin(login) != null) {
            return "Login taken";
        }
        UserEntity u = new UserEntity();
        u.setPassword(password);
        u.setLogin(login);
        userService.saveUser(u);
        return "Ok";
    }
}
