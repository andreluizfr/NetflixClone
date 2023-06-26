package com.example.NetflixClone.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.NetflixClone.Models.enums.Plan;
import com.example.NetflixClone.Models.records.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.mercadopago.resources.payment.Payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Account")
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    // @JsonBackReference
    @OneToOne(mappedBy = "account")
    private User user;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "current_plan", nullable = true)
    private Plan currentPlan;
 
    //1 mês após fazer o pagamento
    @Column(name = "plan_expire_date", nullable = true) 
    private LocalDate planExpireDate;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payment_history")
    private List<Payment> paymentHistory;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "profiles")
    private List<Profile> profiles;

    @Column(name = "limit_of_profiles", nullable = true)
    private int limitOfProfiles;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Account() {
        this.isActive = false;
        this.paymentHistory = new ArrayList<Payment>();
        this.profiles = new ArrayList<Profile>();
        this.limitOfProfiles = 0;
        this.createdAt = LocalDateTime.now();
    }

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
                "  id: " + this.user + "\n" +
                "  id: " + this.currentPlan.toString() + "\n" +
                "  id: " + this.isActive + "\n" +
                "  id: " + this.paymentHistory.toString() + "\n" +
                "  id: " + this.profiles.toString() + "\n" +
                "  id: " + this.limitOfProfiles + "\n" +
                "  id: " + this.createdAt.toString() + "\n" +
                "}";
    }

    public void setLimitOfProfiles(int limitOfProfiles) {
        this.limitOfProfiles = limitOfProfiles;
    }

}
