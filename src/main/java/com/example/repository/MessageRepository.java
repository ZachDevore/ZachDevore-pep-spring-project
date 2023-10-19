package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    
    //I don't understand why this approach didn't work it would return the number of rows affected and pass that into the body seems like a better approach than how I got it working
    @Query(value = "UPDATE message SET message_text = :newMessageText WHERE posted_by = :id", nativeQuery = true)
    int updateMessageById(int id, String newMessageText);
    

    @Query(value ="SELECT * FROM message WHERE posted_by = :id", nativeQuery = true)
    List<Message> getAllMessagesById(int id);

    // Pushing for gitlab bug
}
