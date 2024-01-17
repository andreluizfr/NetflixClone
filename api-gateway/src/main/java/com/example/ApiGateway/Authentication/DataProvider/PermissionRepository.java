package com.example.ApiGateway.Authentication.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiGateway.Authentication.Models.Permission;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    public Optional<Permission> findById(Long id);
}
