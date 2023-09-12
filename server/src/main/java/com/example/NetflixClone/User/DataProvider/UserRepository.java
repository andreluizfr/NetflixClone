package com.example.NetflixClone.User.DataProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.NetflixClone.User.Models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findOneByEmail(String email);

    UserDetails findByEmail(String email);

    List<User> findByAccountId(UUID id);

    List<User> findByCreatedAtBetween(LocalDateTime to, LocalDateTime from);
}
