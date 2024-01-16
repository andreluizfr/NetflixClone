package com.example.ApiGateway.Authentication.Models.Enums;

public enum UserRole {
    ADMIN(Short.valueOf("1")), 
    STAFF(Short.valueOf("2")), 
    USER(Short.valueOf("3"));

    private final Short roleValue;

    UserRole(Short roleValue) {
        this.roleValue = roleValue;
    }

    public Short getRoleValue() {
        return roleValue;
    }

    public static UserRole valueOfShort(Short number) {
        if (number.equals(Short.valueOf((short) 1))) return UserRole.ADMIN;
        if (number.equals(Short.valueOf((short) 2))) return UserRole.STAFF;
        if (number.equals(Short.valueOf((short) 3))) return UserRole.USER;
        throw new RuntimeException("Undefined UserRole");
    }
}