package com.example.ApiGateway.Authentication.DataProvider;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiGateway.Authentication.Models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
