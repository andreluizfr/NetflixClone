package com.example.UserService.Authorization.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserService.Authorization.Models.Permission;
import com.example.UserService.User.Models.Enums.UserRole;

import java.util.List;
import java.util.Optional;


public interface PermissionRepository extends JpaRepository<Permission, Long> {
    public Optional<Permission> findById(Long id);
    public List<Permission> findByRolesIn(UserRole role);
}
