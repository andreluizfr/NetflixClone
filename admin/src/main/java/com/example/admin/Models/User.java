package com.example.admin.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.admin.Models.enums.UserRole;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "role", nullable = false)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account; // Foreign Key, Owner of one-to-one relation key

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public User(String email, String password, String birthDate, UserRole role, Account account) {
        this.email = email;
        this.password = this.hashPassword(password);
        this.birthDate = birthDate;
        this.role = role;
        this.account = account;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object arg0) {
        User otherUser = (User) arg0;
        return this.id.equals(otherUser.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "  id: " + this.id + "\n" +
                "  id: " + this.email + "\n" +
                "  id: " + this.password + "\n" +
                "  id: " + this.birthDate.toString() + "\n" +
                "  id: " + this.role + "\n" +
                "  id: " + this.account.toString() + "\n" +
                "  id: " + this.createdAt.toString() + "\n" +
                "}";
    }

    public String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
