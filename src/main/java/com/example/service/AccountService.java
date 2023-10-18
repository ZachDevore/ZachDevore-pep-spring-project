package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Add a new account
    public ResponseEntity<Account> addNewUser(Account account) {
        /*
         * The username is not already taken
         * The username is not blank
         * The password is at least 4 characters long
         */
        if (accountRepository.getAccountByUsername(account.getUsername()).isPresent()) {
            return ResponseEntity.status(409).body(null);
            
        } else if (account.getUsername() != null && account.getPassword().length() >= 4) {
            return ResponseEntity.ok().body(accountRepository.save(account));
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    // Log In
    public ResponseEntity<Optional<Account>> logIn(String username, String password) {
        if (accountRepository.logIn(username, password).isPresent()) {
            return ResponseEntity.ok().body(accountRepository.logIn(username, password));
        } else {
            return ResponseEntity.status(401).body(null);
        } 
    }
}
