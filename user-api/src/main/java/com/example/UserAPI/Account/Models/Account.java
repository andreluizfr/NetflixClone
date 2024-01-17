package com.example.UserAPI.Account.Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import com.example.UserAPI.Account.Models.Enums.Plan;
import com.example.UserAPI.Account.Models.Enums.PlanConverter;
import com.example.UserAPI.Profile.Models.Profile;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Audited
@Entity(name = "Account")
@Table(name = "account")
@TypeDefs({
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Account {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "active_flag", nullable = false)
    private boolean isActive = false;

    @Convert(converter = PlanConverter.class)
    @Column(name = "current_plan", nullable = true, columnDefinition = "SMALLINT")
    private Plan currentPlan;

    @Column(name = "plan_expire_day", nullable = true, columnDefinition = "SMALLINT")
    private short planExpireDay;

    @Type(type = "jsonb")
    @Column(name = "payment_history", nullable = false)
    private List<UUID> paymentHistory = new ArrayList<UUID>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private List<Profile> profiles = new ArrayList<Profile>();

    @Column(name = "limit_of_profiles", nullable = true)
    private short limitOfProfiles;

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
                "  currentPlan: " + this.currentPlan + "\n" +
                "  isActive: " + this.isActive + "\n" +
                "  paymentHistory: " + this.paymentHistory.toString() + "\n" +
                "  profiles: " + this.profiles.toString() + "\n" +
                "  limitOfProfiles: " + this.limitOfProfiles + "\n" +
                "  createdAt: " + this.createdAt.toString() + "\n" +
                "}";
    }
}
