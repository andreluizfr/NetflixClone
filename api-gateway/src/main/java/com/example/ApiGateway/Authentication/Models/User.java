package com.example.ApiGateway.Authentication.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ApiGateway.Authentication.Models.Enums.UserRole;
import com.example.ApiGateway.Authentication.Models.Enums.UserRoleConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity(name = "User")
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "enabled_flag", nullable = false)
    private Boolean enabled = false;

    @Column(name = "deleted_flag", nullable = false)
    private Boolean deleted = false;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Convert(converter = UserRoleConverter.class)
    @Column(name = "role_id", nullable = false, columnDefinition = "SMALLINT")
    private UserRole role;

    @Transient
    Set<String> permissionsByRole = new HashSet<>();

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
                "  role: " + this.role + "\n" +
                "  createdAt: " + this.createdAt.toString() + "\n" +
                "  updatedAt: " + this.updatedAt.toString() + "\n" +
                "}";
    }

    public void setPassword() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

    public boolean matchesPassword(String password) {
        return new BCryptPasswordEncoder().matches(password, this.password);
    }

    @Transient
    public List<String> getAuthorities() {
        List<String> roleAuthorities = new ArrayList<>();

        if (UserRole.ADMIN.equals(this.role))
            roleAuthorities = List.of(new String("ROLE_ADMIN"));
        else if(UserRole.STAFF.equals(this.role))
            roleAuthorities = List.of(new String("ROLE_STAFF"));
        else
            roleAuthorities = List.of(new String("ROLE_USER"));
        
        return Stream.concat(
                    roleAuthorities.stream(),
                    this.getPermissionsByRole().stream()
                ).collect(Collectors.toList());
    }
}
