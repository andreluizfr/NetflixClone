package com.example.UserAPI.Authorization.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserAPI.Authorization.Models.Permission;

import java.util.Optional;


public interface PermissionRepository extends JpaRepository<Permission, Long> {
    public Optional<Permission> findById(Long id);
    /* 
    @Query("SELECT p FROM Permission p WHERE :role IN (p.roles)")
    public List<Permission> findByRolesIn(@Param("role") UserRole role);
    */
}
