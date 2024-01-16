package com.example.ApiGateway.Authentication.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiGateway.Authentication.Models.Role;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Short> {
    public Optional<Role> findById(Short role);
}
