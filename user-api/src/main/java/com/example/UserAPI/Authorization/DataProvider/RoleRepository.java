package com.example.UserAPI.Authorization.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserAPI.Authorization.Models.Role;
import com.example.UserAPI.User.Models.Enums.UserRole;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, UserRole> {
    public Optional<Role> findById(UserRole role);
}
