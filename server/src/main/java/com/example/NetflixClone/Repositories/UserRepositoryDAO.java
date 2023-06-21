package com.example.NetflixClone.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Models.User;

public interface UserRepositoryDAO extends JpaRepository<User, UUID> {
    List<User> findByEmail(String email);

    List<User> findByAccountId(UUID id);

    List<User> findByCreatedAtBetween(LocalDateTime to, LocalDateTime from);
}
