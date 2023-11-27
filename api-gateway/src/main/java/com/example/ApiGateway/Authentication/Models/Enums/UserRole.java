package com.example.ApiGateway.Authentication.Models.Enums;

public enum UserRole {
    ADMIN(1), 
    STAFF(2), 
    USER(3);

    private final int roleValue;

    UserRole(int roleValue) {
        this.roleValue = roleValue;
    }

    public int getRoleValue() {
        return roleValue;
    }
}