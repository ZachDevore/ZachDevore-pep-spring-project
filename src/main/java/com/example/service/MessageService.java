package com.example.service;


import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // Get all messages
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return ResponseEntity.ok().body(messages);
    }

    // Get message by id
    public ResponseEntity<Message> getMessageById(int message_id) {
        if (messageRepository.findById(message_id).isPresent()) {
            return ResponseEntity.ok().body(messageRepository.findById(message_id).get());
        } else {
            return ResponseEntity.ok().body(null);
        }
        
    }

    // Delete message by id
    public ResponseEntity<Integer> deleteMessageById(int message_id) {
        if (messageRepository.findById(message_id).isPresent()) {
            messageRepository.deleteById(message_id);
            return ResponseEntity.ok().body(1);
        } else {
            return ResponseEntity.ok(0);
        }
    }

    // Update message by id
    public ResponseEntity<Integer> updateMessageById(int message_id, String messageText) {
        /*
         * message_id already exist
         * message_text is not blank
         * message_text not over 255 characters
         */
        if (messageRepository.findById(message_id).isPresent()
            && !messageText.isEmpty()
            && messageText.length() <= 255) {

            Message messageToBeUpdated = messageRepository.getById(message_id);
            messageToBeUpdated.setMessage_text(messageText);
            messageRepository.save(messageToBeUpdated);

            return ResponseEntity.ok().body(1);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    
    }

    // Get all messages by account id
    public ResponseEntity<List<Message>> getAllMessagesByUser(int id) {
        List<Message> messages = messageRepository.getAllMessagesById(id);
        return ResponseEntity.ok().body(messages);
    }
}
