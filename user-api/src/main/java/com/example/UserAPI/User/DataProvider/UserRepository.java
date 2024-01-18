package com.example.UserAPI.User.DataProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserAPI.User.Models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    List<User> findByAccountId(UUID id);

    List<User> findByCreatedAtBetween(LocalDateTime to, LocalDateTime from);
}
