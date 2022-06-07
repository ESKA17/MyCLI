package com.example.mycli.controller;

import com.example.mycli.MyCliApplication;
import com.example.mycli.dao.AccountRepositoryDAO;
import com.example.mycli.dao.TransactionRepositoryDAO;
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
        if (Objects.equals(accountType, "CHECKING")) {
            bankCore.createNewAccount(AccountType.CHECKING, myCliApplication.clientID);
            return ResponseEntity.status(HttpStatus.OK).body("CHECKING account was created\n");
        } else if (Objects.equals(accountType, "SAVING")) {
            bankCore.createNewAccount(AccountType.SAVING, myCliApplication.clientID);
            return ResponseEntity.status(HttpStatus.OK).body("SAVING account was created\n");
        } else if (Objects.equals(accountType, "FIXED")) {
            bankCore.createNewAccount(AccountType.FIXED, myCliApplication.clientID);
            return ResponseEntity.status(HttpStatus.OK).body("FIXED account was created\n");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!\n");
        }
    }

    @GetMapping("/{account_id}")
    ResponseEntity<String> getAccount(@PathVariable String account_id) {
        String out = accountRepositoryDAO.findById(account_id).toString();
        try {
            if (Integer.parseInt(account_id) >= 1000001) {
                return ResponseEntity.status(HttpStatus.OK).body(out);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
        }
    }

    @DeleteMapping("/{account_id}")
    ResponseEntity<String> deleteAccount(@PathVariable String account_id) {
        try {
            if (Integer.parseInt(account_id) >= 1000001) {
                accountRepositoryDAO.deleteById(account_id);
                return ResponseEntity.status(HttpStatus.OK).body("Account was deleted!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
        }

    }

    @PostMapping("/{account_id}/withdraw")
    ResponseEntity<String> withdrawMoney(@PathVariable String account_id, @RequestParam double amount) {
        try {
            if (Integer.parseInt(account_id) >= 1000001) {
                Account account = accountRepositoryDAO.findById(account_id).orElse(null);
                assert account != null;
                if (account.isWithdrawalAllowed()) {
                    if (amount > 0) {
                        if (account.getBalance() >= amount) {
                            transactionWithdraw.execute((AccountWithdraw) account, amount);
                            return ResponseEntity.status(HttpStatus.OK).body(String.format("%.2f", amount) +
                                    "$ transferred to " + account_id + " account");
                        } else {
                            return ResponseEntity.status(HttpStatus.OK).body("Not enough funds!");
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount should be greater than 0!");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("Withdrawal is not allowed!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
        }
    }

    @PostMapping("/{account_id}/deposit")
    ResponseEntity<String> depositMoney(@PathVariable String account_id, @RequestParam double amount) {
        try {
            if (Integer.parseInt(account_id) >= 1000001) {
                if (amount > 0) {
                    Account account = accountRepositoryDAO.findById(account_id).orElse(null);
                    transactionDeposit.execute(account, amount);
                    return ResponseEntity.status(HttpStatus.OK).body(String.format("%.2f", amount) +
                            "$ transferred to " + account_id + " account");
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("Amount should be greater than 0");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
        }
    }

    @GetMapping("/{account_id}/transactions")
    ResponseEntity<?> getAllTransactions(@PathVariable String account_id) {

        try {
            if (Integer.parseInt(account_id) >= 1000001) {
                List<Transaction> out = new ArrayList<>();
                Iterable<Transaction> iterable = transactionRepositoryDAO.findAll();
                iterable.forEach(transaction -> {
                    if (Objects.equals(transaction.getId(), account_id)) out.add(transaction);
                });
                return ResponseEntity.status(HttpStatus.OK).body(out);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
        }
    }
    @GetMapping("/transactions/{tr_id}")
    Transaction getAllTransactions(@PathVariable long tr_id) {
        return transactionRepositoryDAO.getTransactionByTransactionID(tr_id);

    }
}

