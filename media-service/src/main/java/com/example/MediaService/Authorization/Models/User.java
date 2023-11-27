package com.example.MediaService.Authorization.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.MediaService.Authorization.CustomPermission;
import com.example.MediaService.Authorization.Models.Enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "user_info")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "enabled_flag", nullable = false)
    private Boolean enabled = false;

    @Column(name = "deleted_flag", nullable = false)
    private Boolean deleted = false;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private UserRole role;

    @Transient
    List<CustomPermission> permissionsNamesByRole = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false) 
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        
        User otherUser = (User) obj;
        if (this.id != null){
            return this.id.equals(otherUser.id);
        } 
        if (otherUser.id != null){
            return otherUser.id.equals(this.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "  id: " + this.id + "\n" +
                "  enabled: " + this.enabled + "\n" +
                "  deleted: " + this.deleted + "\n" +
                "  email: " + this.email + "\n" +
                "  password: " + this.password + "\n" +
                "  role: " + this.role + "\n" +
                "  createdAt: " + this.createdAt.toString() + "\n" +
                "  updatedAt: " + this.updatedAt.toString() + "\n" +
                "}";
    }

    public void setPassword() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

    @Override
    @Transient
    public Collection<CustomPermission> getAuthorities() {
        List<CustomPermission> existingPermissions;

        if (this.role == UserRole.ADMIN)
            existingPermissions = List.of(new CustomPermission("ROLE_ADMIN"));
        if(this.role == UserRole.STAFF)
            existingPermissions = List.of(new CustomPermission("ROLE_STAFF"));
        else
            existingPermissions = List.of(new CustomPermission("ROLE_USER"));
        
        existingPermissions.addAll(getPermissionsNamesByRole());

        return existingPermissions;
    }

    @Transient
    @Override
    public String getUsername() {
        return this.email;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return this.enabled && !this.deleted;
    }
}
