package com.example.MediaAPI.Authorization;

import org.springframework.security.core.GrantedAuthority;

public class CustomPermission implements GrantedAuthority {
    private String permission;

    public CustomPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String getAuthority() {
        return permission;
    }
}