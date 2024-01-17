package com.example.ApiGateway.Authentication.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiGateway.Authentication.Models.Role;
import com.example.ApiGateway.Authentication.Models.Enums.UserRole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, UserRole> {
    public Optional<Role> findById(UserRole role);
}
