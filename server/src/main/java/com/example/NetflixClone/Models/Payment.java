package com.example.NetflixClone.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.UUID;

import com.example.NetflixClone.Models.enums.Method;
import com.example.NetflixClone.Models.enums.Plan;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Payment")
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @Column(name = "method")
    private Method method;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan")
    private Plan plan;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal paidValue;

    @Override
    public boolean equals(Object arg0) {
        Payment otherPayment = (Payment) arg0;
        return this.id.equals(otherPayment.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "  id: " + this.id.toString() + "\n" +
                "  id: " + this.account + "\n" +
                "  id: " + this.method.toString() + "\n" +
                "  id: " + this.dateTime + "\n" +
                "  id: " + this.plan.toString() + "\n" +
                "  id: " + this.paidValue + "\n" +
                "}";
    }

}
