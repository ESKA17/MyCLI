package com.example.mycli.web;

import com.example.mycli.MyCliApplication;
import com.example.mycli.exceptions.AuthenticationFailed;
import com.example.mycli.model.Account;
import com.example.mycli.model.Transaction;
import com.example.mycli.model.UserEntity;
import com.example.mycli.repository.AccountRepository;
import com.example.mycli.repository.TransactionRepository;
import com.example.mycli.exceptions.AccountBadRequest;
import com.example.mycli.exceptions.AccountNotFound;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.server.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountTransactionController {
    private final AccountRepository accountRepository;
    private final TransactionWithdraw transactionWithdraw;
    private final TransactionDeposit transactionDeposit;
    private final TransactionRepository transactionRepository;
    private final BankCore bankCore;
    private final UserEntityRepository userEntityRepository;
    private final MyCliApplication myCliApplication;

    @GetMapping("/all")
    ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountRepository.findAllByUserEntity(getUser()));
    }

    @PostMapping()
    ResponseEntity<String> createAccount(@RequestParam String accountType) {
        if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("fixed") ||
                accountType.equalsIgnoreCase("saving")) {
            bankCore.createNewAccount(accountType, myCliApplication.clientID);
            Account modifiedAccount = accountRepository.findTopByOrderByIdDesc();
            modifiedAccount.setUserEntity(getUser());
            accountRepository.save(modifiedAccount);
            return ResponseEntity.status(HttpStatus.OK).body(accountType.toUpperCase() + " account was created\n");
        } else {
            throw new AccountBadRequest(accountType);
        }
    }

    @GetMapping("/{account_id}")
    ResponseEntity<?> getAccount(@PathVariable String account_id) {
        Account out = exceptionChecker(account_id);
        return ResponseEntity.status(HttpStatus.OK).body(out);
    }
    @DeleteMapping("/{account_id}")
    ResponseEntity<?> deleteAccount(@PathVariable String account_id) {
        exceptionChecker(account_id);
        accountRepository.deleteById(account_id);
        return ResponseEntity.status(HttpStatus.OK).body("Account was deleted");
    }

    @PostMapping("/{account_id}/withdraw")
    ResponseEntity<?> withdrawMoney(@PathVariable String account_id, @RequestParam double amount) {
        Account account = exceptionChecker(account_id);
        if (!account.getWithdrawalAllowed()) {
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

    @PostMapping("/{account_id}/transfer")
    ResponseEntity<?> transferMoney(@PathVariable String account_id, @RequestBody @Valid TransferRequest transfer) {
        withdrawMoney(account_id, transfer.getAmount());
        depositMoney(transfer.getDestination_account_id(), transfer.getAmount());
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping("/{account_id}/deposit")
    ResponseEntity<?> depositMoney(@PathVariable String account_id, @RequestParam double amount) {
        Account account = exceptionChecker(account_id);
        if (amount > 0) {
            transactionDeposit.execute(account, amount);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("%.2f", amount) +
                    "$ transferred to " + account_id + " account");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount should be greater than 0");
        }
    }
    @GetMapping("/{account_id}/transactions")
    ResponseEntity<?> getAllTransactions(@PathVariable String account_id) {
        exceptionChecker(account_id);
        List<Transaction> transactionsList = transactionRepository.findTransactionById(account_id);
        return ResponseEntity.status(HttpStatus.OK).body(transactionsList);
    }

    private Account exceptionChecker(@PathVariable String account_id) {
        if (!account_id.matches("\\d+")) throw new AccountBadRequest(account_id);
        if (accountRepository.findAccountById(account_id) == null) throw new AccountNotFound(account_id);
        Account out = accountRepository.findAccountByIdAndUserEntity(account_id, getUser());
        if (accountRepository.count() != 0 && out == null) throw new AuthenticationFailed();
        return out;
    }
    private UserEntity getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userEntityRepository.findByLogin(userName);
    }
}

