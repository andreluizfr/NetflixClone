package com.example.UserAPI.UserActivity.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.UserAPI.Profile.Models.Profile;
import com.example.UserAPI.User.Models.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserActivity")
@Table(name = "user_activity", indexes = {
    @Index(columnList = "created_at", name = "ix_user_activity_created_at")
})
public class UserActivity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = true)
    private Profile profile;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "permission_name", nullable = false)
    private String permissionName;

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
        
        UserActivity otherUserActivity = (UserActivity) obj;
        if (this.id != null){
            return this.id.equals(otherUserActivity.id);
        } 
        if (otherUserActivity.id != null){
            return otherUserActivity.id.equals(this.id);
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
                "  user: " + this.user.toString() + "\n" +
                "  profile: " + this.profile.toString() + "\n" +
                "  ip: " + this.ip + "\n" +
                "  permissionName: " + this.permissionName + "\n" +
                "  createdAt: " + this.createdAt.toString() + "\n" +
                "  updatedAt: " + this.updatedAt.toString() + "\n" +
                "}";
    }
}
