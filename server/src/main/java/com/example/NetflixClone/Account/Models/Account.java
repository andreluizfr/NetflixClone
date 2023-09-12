package com.example.NetflixClone.Account.Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.example.NetflixClone.Account.Models.Enums.Plan;
import com.example.NetflixClone.Profile.Models.Profile;
import com.example.NetflixClone.User.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadopago.resources.preference.Preference;

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
@Entity(name = "Account")
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private User user;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = false;

    @Column(name = "current_plan", nullable = true)
    private Plan currentPlan;

    // 1 mês após fazer o pagamento
    @Column(name = "plan_expire_date", nullable = true)
    private LocalDateTime planExpireDate;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payment_history", nullable = false)
    private List<Preference> paymentHistory = new ArrayList<Preference>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "account_id") 
    private List<Profile> profiles = new ArrayList<Profile>();;

    @Column(name = "limit_of_profiles", nullable = true)
    private Integer limitOfProfiles;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false) 
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object arg0) {
        Account otherAccount = (Account) arg0;
        return this.id.equals(otherAccount.id);
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

    public void setLimitOfProfiles(int limitOfProfiles) {
        this.limitOfProfiles = limitOfProfiles;
    }

}
