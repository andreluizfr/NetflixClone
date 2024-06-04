package com.example.MediaAPI.Media.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MediaList")
@Table(name = "mediaList")
public class MediaList implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "title", nullable = false)
    String title;

    @ManyToMany()
    @Fetch(FetchMode.JOIN)
    @JoinTable(
        name = "media_list_and_media",
        joinColumns = @JoinColumn(name = "media_list_id"),
        inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    private List<Media> medias;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Version
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false) 
    private LocalDateTime updatedAt;

    public MediaList(String title, List<Media> medias) {
        this.title = title;
        this.medias = medias;
    }
}
