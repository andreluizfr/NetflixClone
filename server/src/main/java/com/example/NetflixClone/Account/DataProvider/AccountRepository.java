package com.example.NetflixClone.Account.DataProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Account.Models.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findByCreatedAtBetween(LocalDateTime to, LocalDateTime from);
}
