package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> getAccountByUsername(String userName);

    @Query(value= "SELECT * FROM ACCOUNT WHERE username = :username AND password = :password", nativeQuery = true)
    Optional<Account> logIn(String username, String password);
}
