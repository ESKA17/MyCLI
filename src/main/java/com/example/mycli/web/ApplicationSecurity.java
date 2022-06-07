//package com.example.mycli.web;
//
//import com.example.mycli.dao.AccountRepositoryDAO;
//import lombok.AllArgsConstructor;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//@EnableWebSecurity
//@AllArgsConstructor
//public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//    private final AccountRepositoryDAO accountRepositoryDAO;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(username -> accountRepositoryDAO
//                .findByUsername(username)
//                .orElseThrow(
//                        () -> new UsernameNotFoundException(
//                                format("User: %s, not found", username)
//                        )
//                ));
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    }
//}
