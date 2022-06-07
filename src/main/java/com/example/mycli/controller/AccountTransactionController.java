package com.example.mycli.controller;

import com.example.mycli.MyCliApplication;
import com.example.mycli.dao.AccountRepositoryDAO;
import com.example.mycli.dao.TransactionRepositoryDAO;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.server.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountTransactionController {
    private final AccountRepositoryDAO accountRepositoryDAO;
    private final TransactionWithdraw transactionWithdraw;
    private final TransactionDeposit transactionDeposit;
    private final TransactionRepositoryDAO transactionRepositoryDAO;
    private final BankCore bankCore;
    private final MyCliApplication myCliApplication;

    @GetMapping()
    ResponseEntity<List<Account>> getAllAccounts() {
        Iterable<Account> iterable = accountRepositoryDAO.findAll();
        List<Account> out =  StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(out);
    }

    @PostMapping()
    ResponseEntity<String> createAccount(@RequestParam String accountType) {
        if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("fixed") ||
                accountType.equalsIgnoreCase("saving")) {
            bankCore.createNewAccount(accountType, myCliApplication.clientID);
            return ResponseEntity.status(HttpStatus.OK).body(accountType.toUpperCase() + " account was created\n");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!\n");
        }
    }

    @GetMapping("/{account_id}")
    ResponseEntity<?> getAccount(@PathVariable String account_id) {
        ResponseEntity<?> match = mathcer(account_id);
        if (Objects.equals(match.getStatusCode(), HttpStatus.OK)) {
                Account out = accountRepositoryDAO.findById(account_id).orElseThrow(() ->
                        new AccountNotFound(account_id));
                return ResponseEntity.status(HttpStatus.OK).body(out);
        } else {
            return match;
        }
    }

    @DeleteMapping("/{account_id}")
    ResponseEntity<?> deleteAccount(@PathVariable String account_id) {
        ResponseEntity<?> match = mathcer(account_id);
        if (Objects.equals(match.getStatusCode(), HttpStatus.OK)) {
            accountRepositoryDAO.deleteById(account_id);
            return ResponseEntity.status(HttpStatus.OK).body("Account was deleted");
        } else {
            return match;
        }
    }

    @PostMapping("/{account_id}/withdraw")
    ResponseEntity<?> withdrawMoney(@PathVariable String account_id, @RequestParam double amount) {
        ResponseEntity<?> match = mathcer(account_id);
        if (Objects.equals(match.getStatusCode(), HttpStatus.OK)) {
            Account account = accountRepositoryDAO.findById(account_id).orElseThrow(() ->
                    new AccountNotFound(account_id));
            if (!account.isWithdrawalAllowed()) {
                return ResponseEntity.status(HttpStatus.OK).body("Withdrawal is not allowed!");
            }
            if (amount > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount should be greater than 0!");
            }
            if (account.getBalance() >= amount) {
                transactionWithdraw.execute((AccountWithdraw) account, amount);
                return ResponseEntity.status(HttpStatus.OK).body(String.format("%.2f", amount) +
                        "$ transferred to " + account_id + " account");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Not enough funds!");
            }
        } else {
            return match;
        }

    }

    @PostMapping("/{account_id}/deposit")
    ResponseEntity<?> depositMoney(@PathVariable String account_id, @RequestParam double amount) {
        ResponseEntity<?> match = mathcer(account_id);
        if (Objects.equals(match.getStatusCode(), HttpStatus.OK)) {
            if (amount > 0) {
                Account account = accountRepositoryDAO.findById(account_id).orElseThrow(() ->
                        new AccountNotFound(account_id));
                transactionDeposit.execute(account, amount);
                return ResponseEntity.status(HttpStatus.OK).body(String.format("%.2f", amount) +
                        "$ transferred to " + account_id + " account");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount should be greater than 0");
            }
        } else {
            return match;
        }

    }
    @GetMapping("/{account_id}/transactions")
    ResponseEntity<?> getAllTransactions(@PathVariable String account_id) {
        ResponseEntity<?> match = mathcer(account_id);
        if (Objects.equals(match.getStatusCode(), HttpStatus.OK)) {
            List<Transaction> out = new ArrayList<>();
            Iterable<Transaction> iterable = transactionRepositoryDAO.findAll();
            iterable.forEach(transaction -> {
                if (Objects.equals(transaction.getId(), account_id)) out.add(transaction);
            });
            return ResponseEntity.status(HttpStatus.OK).body(out);
        } else {
            return match;
        }

    }
    ResponseEntity<?> mathcer(String account_id) {
        if (!account_id.matches("[0-9]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
        }
        accountRepositoryDAO.findById(account_id).orElseThrow(() -> new AccountNotFound(account_id));
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
