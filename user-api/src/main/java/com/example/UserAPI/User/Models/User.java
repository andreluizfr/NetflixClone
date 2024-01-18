package com.example.UserAPI.User.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.UserAPI.Account.Models.Account;
import com.example.UserAPI.Authorization.Models.CustomPermission;
import com.example.UserAPI.User.Models.Enums.UserRole;
import com.example.UserAPI.User.Models.Enums.UserRoleConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Audited
@Entity(name = "User")
@Table(name = "user_info")
public class User implements UserDetails{

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "enabled_flag", nullable = false)
    private Boolean enabled = true;

    @Column(name = "deleted_flag", nullable = false)
    private Boolean deleted = false;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Convert(converter = UserRoleConverter.class)
    @Column(name = "role_id", nullable = false, columnDefinition = "SMALLINT")
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

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

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    @Transient
    public Collection<CustomPermission> getAuthorities() {
        List<CustomPermission> existingPermissions = new ArrayList<>();

        if (UserRole.ADMIN.equals(this.role))
            existingPermissions = List.of(new CustomPermission("ROLE_ADMIN"));
        else if(UserRole.STAFF.equals(this.role))
            existingPermissions = List.of(new CustomPermission("ROLE_STAFF"));
        else
            existingPermissions = List.of(new CustomPermission("ROLE_USER"));
        
        return Stream.concat(
                    this.getPermissionsNamesByRole().stream(),
                    existingPermissions.stream()
                ).collect(Collectors.toList());
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
