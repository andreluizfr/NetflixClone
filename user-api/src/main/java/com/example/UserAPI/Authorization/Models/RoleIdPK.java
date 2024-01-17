package com.example.UserAPI.Authorization.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;

import com.example.UserAPI.User.Models.Enums.UserRole;
import com.example.UserAPI.User.Models.Enums.UserRoleConverter;

public class RoleIdPK implements Serializable {
    @Column(name = "id")
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;
}
