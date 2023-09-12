package com.example.admin.Models;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Profile")
@Table(name = "Profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "icon_cod", nullable = false)
    private int iconCod;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "profile_and_seen_medias", 
        joinColumns = @JoinColumn(name = "profile_id"), 
        inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    private Set<Media> seenMedias;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "preferences", nullable = true)
    private Object preferences;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object arg0) {
        Profile otherProfile = (Profile) arg0;
        return this.id.equals(otherProfile.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
