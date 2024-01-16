package com.example.ApiGateway.Authentication.Models.Enums;

import javax.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<UserRole, Short> {

    @Override
    public Short convertToDatabaseColumn(final UserRole role) {

        if (role == null) {
            return null;
        }
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