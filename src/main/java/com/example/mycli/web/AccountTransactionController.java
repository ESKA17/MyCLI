package com.example.mycli.web;

import com.example.mycli.MyCliApplication;
import com.example.mycli.model.Account;
import com.example.mycli.model.Transaction;
import com.example.mycli.repository.AccountRepository;
import com.example.mycli.repository.TransactionRepository;
import com.example.mycli.exceptions.AccountBadRequest;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.server.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountTransactionController {
    private final AccountRepository accountRepository;
    private final TransactionWithdraw transactionWithdraw;
    private final TransactionDeposit transactionDeposit;
    private final TransactionRepository transactionRepository;
    private final BankCore bankCore;
    private final MyCliApplication myCliApplication;

    @GetMapping("/all")
    ResponseEntity<List<Account>> getAllAccounts() {
        Iterable<Account> iterable = accountRepository.findAll();
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
        digitCheck(account_id);
        Account out = accountRepository.findById(account_id).orElseThrow(() ->
                new AccountNotFound(account_id));
        return ResponseEntity.status(HttpStatus.OK).body(out);
    }

    @DeleteMapping("/{account_id}")
    ResponseEntity<?> deleteAccount(@PathVariable String account_id) {
        digitCheck(account_id);
        if (accountRepository.findById(account_id).isEmpty()) throw new AccountNotFound(account_id);
        accountRepository.deleteById(account_id);
        return ResponseEntity.status(HttpStatus.OK).body("Account was deleted");
    }

    @PostMapping("/{account_id}/withdraw")
    ResponseEntity<?> withdrawMoney(@PathVariable String account_id, @RequestParam double amount) {
        digitCheck(account_id);
        Account account = accountRepository.findById(account_id).orElseThrow(() ->
                new AccountNotFound(account_id));
        if (!account.isWithdrawalAllowed()) {
            return ResponseEntity.status(HttpStatus.OK).body("Withdrawal is not allowed!");
        }
        if (amount <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount should be greater than 0!");
        }
        if (account.getBalance() >= amount) {
            transactionWithdraw.execute((AccountWithdraw) account, amount);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("%.2f", amount) +
                    "$ transferred from " + account_id + " account");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Not enough funds!");
        }
    }

    @PostMapping("/{account_id}/deposit")
    ResponseEntity<?> depositMoney(@PathVariable String account_id, @RequestParam double amount) {
        digitCheck(account_id);
        if (amount > 0) {
            Account account = accountRepository.findById(account_id).orElseThrow(() ->
                    new AccountNotFound(account_id));
            transactionDeposit.execute(account, amount);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("%.2f", amount) +
                    "$ transferred to " + account_id + " account");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount should be greater than 0");
        }
    }
    @GetMapping("/{account_id}/transactions")
    ResponseEntity<?> getAllTransactions(@PathVariable String account_id) {
        digitCheck(account_id);
        if (accountRepository.findById(account_id).isEmpty()) throw new AccountNotFound(account_id);
        List<Transaction> out = new ArrayList<>();
        Iterable<Transaction> iterable = transactionRepository.findAll();
        iterable.forEach(transaction -> {
            if (Objects.equals(transaction.getId(), account_id)) out.add(transaction);
        });
        return ResponseEntity.status(HttpStatus.OK).body(out);
    }
    void digitCheck(String account_id) {
        if (!account_id.matches("\\d+")) throw new AccountBadRequest(account_id);
    }
}
