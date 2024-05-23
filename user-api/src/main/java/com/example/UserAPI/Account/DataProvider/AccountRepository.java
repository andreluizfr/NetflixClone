package com.example.UserAPI.Account.DataProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.UserAPI.Account.Models.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("SELECT a FROM Account a " +
            "JOIN FETCH a.profiles p " +
            "WHERE a.id = (:id) ")
    Optional<Account> findById(UUID id);

    List<Account> findByCreatedAtBetween(LocalDateTime to, LocalDateTime from);
}
