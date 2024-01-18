package com.example.UserAPI.UserActivity.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.UserAPI.Profile.Models.Profile;
import com.example.UserAPI.User.Models.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_activity_id_seq_gen")
    @SequenceGenerator(name = "user_activity_id_seq_gen", sequenceName = "user_activity_id_seq", allocationSize = 1) 
    @Column(name = "id")
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
