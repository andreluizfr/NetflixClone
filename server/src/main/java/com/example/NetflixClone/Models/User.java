package com.example.NetflixClone.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.example.NetflixClone.Models.enums.Role;

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
@Table(name = "tbl_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "role")
    private Role role;

    // @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account; // Foreign Key, Owner of one-to-one relation key

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User(UserDTO userDTO) {
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.birthDate = LocalDate.parse(userDTO.birthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.role = Role.BASIC;
        this.account = userDTO.account();
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
    /*
     * @Override
     * public String toString() {
     * return "{" +
     * "  id: " + this.id.toString() + "\n" +
     * "  id: " + this.email + "\n" +
     * "  id: " + this.password + "\n" +
     * "  id: " + this.birthDate.toString() + "\n" +
     * "  id: " + this.role.toString() + "\n" +
     * "  id: " + this.account.toString() + "\n" +
     * "  id: " + this.createdAt.toString() + "\n" +
     * "}";
     * }
     */
}
