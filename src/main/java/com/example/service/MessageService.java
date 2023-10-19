package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
        
    }

    // Create a new message
    public ResponseEntity<Message> createNewMessage(Message message) {
        /*
         * message_text is not blank
         * message_text under 255 characters
         * posted_by refers to an existing user
         */
        if (message.getMessage_text() != ""
            && message.getMessage_text().length() < 255
            && accountRepository.existsById(message.getPosted_by().intValue())) {
                return ResponseEntity.ok().body(messageRepository.save(message));
            } else {
                return ResponseEntity.status(400).body(null);
            }
    }
}
