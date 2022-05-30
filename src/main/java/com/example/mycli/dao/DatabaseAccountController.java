package com.example.mycli.dao;

import com.example.mycli.server.Account;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class DatabaseAccountController {
    private final AccountRepositoryDAO accountRepositoryDAO;

    @GetMapping("/accounts")
    List<Account> getAllAccounts() {
        return (List<Account>) accountRepositoryDAO.findAll();
    }

    @PostMapping("/accounts")
    void createAccount(@RequestBody Account newAccount) {
        accountRepositoryDAO.save((newAccount));
    }

    @GetMapping("/accounts/{account_id}")
    Account getAccount(@PathVariable String account_id) {
        return (Account) accountRepositoryDAO.findAllById(Collections.singleton(account_id));
    }

    @DeleteMapping("aacounts/{account_id}")
    void deleteAccount(@PathVariable String account_id) {
        accountRepositoryDAO.deleteById(account_id);
    }

    @PostMapping("/accounts/{account_id}/withdraw")
    void withdrawMoney(@PathVariable String account_id) {
    }
}
