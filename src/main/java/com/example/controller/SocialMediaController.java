package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    // Our API should be able to process new User registrations
    @PostMapping("/register")
    public ResponseEntity<Account> registerNewUser(@RequestBody Account account) {
        return accountService.addNewUser(account);
    }

    // Our API should be able to process User logins

    // Our API should be able to process the creation of new messages

    // Our API should be able to retrieve all messages

    // Our API should be able to retrieve a message by its ID

    // Our API should be able to delete a message identified by a message ID

    // Our API should be able to update a message text identified by a message ID

    // Our API should be able to retrieve all messages written by a particular user

    
}
