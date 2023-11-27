package com.example.UserService.User.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.UserService.Account.Models.Account;
import com.example.UserService.Authorization.CustomPermission;
import com.example.UserService.User.Models.Enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity(name = "User")
@Table(name = "user_info")
public class User implements UserDetails{

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

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "role_id", nullable = false)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_id") 
    private Set<Long> profiles = new HashSet<>();

    @Transient
    List<CustomPermission> permissionsNamesByRole = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Version
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
                "  birthData: " + this.birthDate.toString() + "\n" +
                "  role: " + this.role + "\n" +
                "  account: " + this.account.toString() + "\n" +
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
    
    @Override
    @Transient
    public String getUsername() {
        return this.email;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return this.enabled && !this.deleted;
    }
}
