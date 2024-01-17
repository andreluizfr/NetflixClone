package com.example.ApiGateway.Authentication.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;

import com.example.ApiGateway.Authentication.Models.Enums.UserRole;
import com.example.ApiGateway.Authentication.Models.Enums.UserRoleConverter;

public class RoleIdPK implements Serializable {
    @Column(name = "id")
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;
}
