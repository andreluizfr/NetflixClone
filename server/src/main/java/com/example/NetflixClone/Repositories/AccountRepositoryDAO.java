package com.example.NetflixClone.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Models.Account;

public interface AccountRepositoryDAO extends JpaRepository<Account, UUID> {
    List<Account> findByCreatedAtBetween(LocalDateTime to, LocalDateTime from);
}
