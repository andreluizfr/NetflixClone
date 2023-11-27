package com.example.UserService.Account.Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.type.SqlTypes;

import com.example.UserService.Account.Models.Enums.Plan;
import com.example.UserService.Profile.Models.Profile;
import com.example.UserService.User.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity(name = "Account")
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private User user;

    @Column(name = "active_flag", nullable = false)
    private boolean isActive = false;

    @Column(name = "current_plan", nullable = true)
    private Plan currentPlan;

    @Column(name = "plan_expire_day", nullable = true)
    private short planExpireDay;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payment_history", nullable = false)
    private List<UUID> paymentHistory = new ArrayList<UUID>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "account_id") 
    private List<Profile> profiles = new ArrayList<Profile>();

    @Column(name = "limit_of_profiles", nullable = true)
    private short limitOfProfiles;

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
        
        Account otherAccount = (Account) obj;
        if (this.id != null){
            return this.id.equals(otherAccount.id);
        } 
        if (otherAccount.id != null){
            return otherAccount.id.equals(this.id);
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
                "  id: " + this.id.toString() + "\n" +
                "  user: " + this.user + "\n" +
                "  currentPlan: " + this.currentPlan + "\n" +
                "  isActive: " + this.isActive + "\n" +
                "  paymentHistory: " + this.paymentHistory.toString() + "\n" +
                "  profiles: " + this.profiles.toString() + "\n" +
                "  limitOfProfiles: " + this.limitOfProfiles + "\n" +
                "  createdAt: " + this.createdAt.toString() + "\n" +
                "}";
    }
}
