package com.example.mycli.controller;

import com.example.mycli.MyCliApplication;
import com.example.mycli.dao.AccountRepositoryDAO;
import com.example.mycli.dao.TransactionRepositoryDAO;
import com.example.mycli.server.*;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "This method is used to get all accounts present in database.")
    @GetMapping()
    ResponseEntity<List<Account>> getAllAccounts() {
        Iterable<Account> iterable = accountRepositoryDAO.findAll();
        List<Account> out =  StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(out);
    }

    @ApiOperation(value = "This method is used for creation of a new account.")
    @PostMapping()
    ResponseEntity<String> createAccount(@RequestParam String accountType) {
        if (Objects.equals(accountType, "CHECKING")) {
            bankCore.createNewAccount(AccountType.CHECKING, myCliApplication.clientID);
            return ResponseEntity.status(HttpStatus.CREATED).body("CHECKING account was created\n");
        } else if (Objects.equals(accountType, "SAVING")) {
            bankCore.createNewAccount(AccountType.SAVING, myCliApplication.clientID);
            return ResponseEntity.status(HttpStatus.CREATED).body("SAVING account was created\n");
        } else if (Objects.equals(accountType, "FIXED")) {
            bankCore.createNewAccount(AccountType.FIXED, myCliApplication.clientID);
            return ResponseEntity.status(HttpStatus.CREATED).body("FIXED account was created\n");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!\n");
        }
    }

    @ApiOperation(value = "This method is used to get a specific account from database.")
    @GetMapping("/{account_id}")
    ResponseEntity<String> getAccount(@PathVariable String account_id) {
        String out = accountRepositoryDAO.findById(account_id).toString();
        try {
            if (Integer.parseInt(account_id) >= 1000001) {
                return ResponseEntity.status(HttpStatus.OK).body(out);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
        }
    }

    @ApiOperation(value = "This method is used for deletion of specific account.")
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

    @ApiOperation(value = "This method is used for withdraw of money from specific account.")
    @PostMapping("/{account_id}/withdraw")
    ResponseEntity<String> withdrawMoney(@PathVariable String account_id, @RequestParam double amount) {
        try {
            if (Integer.parseInt(account_id) >= 1000001) {
                Account account = accountRepositoryDAO.findById(account_id).orElse(null);
                assert account != null;
                if (account.isWithdrawalAllowed()) {
                    if (amount > 0) {
                        if (account.getBalance() > amount) {
                            transactionWithdraw.execute((AccountWithdraw) account, amount);
                            return ResponseEntity.status(HttpStatus.OK).body("Money were withdrawn!");
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough funds!");
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount should be greater than 01");
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
                    return ResponseEntity.status(HttpStatus.OK).body("Money were deposited!");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount should be greater than 0");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input!");
        }
    }

    @ApiOperation(value = "This method is used to get all transactions related to specific account.")
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
}
