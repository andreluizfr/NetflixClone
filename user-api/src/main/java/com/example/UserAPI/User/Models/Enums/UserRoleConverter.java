package com.example.UserAPI.User.Models.Enums;

import jakarta.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<UserRole, Short> {

    @Override
    public Short convertToDatabaseColumn(final UserRole role) {
        return role.getRoleValue();
    }

    @Override
    public UserRole convertToEntityAttribute(final Short dbData) {
        if (dbData == 1) {
            return UserRole.ADMIN;
        } else if (dbData == 2) {
            return UserRole.STAFF;
        } else if (dbData == 3) {
            return UserRole.USER;
        } else {
            return null;
        }
    }
}