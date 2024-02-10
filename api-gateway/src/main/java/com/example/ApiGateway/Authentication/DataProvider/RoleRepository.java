package com.example.ApiGateway.Authentication.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiGateway.Authentication.Models.Role;
import com.example.ApiGateway.Authentication.Models.RoleIdPK;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, RoleIdPK> {
    public Optional<Role> findById(RoleIdPK roleIdPK);
}
