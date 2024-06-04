package com.example.MediaAPI.Media.Models;

import com.example.MediaAPI.Media.Models.Enums.Genre;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Media")
@Table(name = "media", indexes = {
    @Index(columnList = "created_at", name = "ix_media_created_at")
})
@TypeDefs({
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Media implements Serializable {

    private static final long serialVersionUID = 6539695099267757690L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="media_media_id_seq_gen")
    @SequenceGenerator(name = "media_media_id_seq_gen", sequenceName = "media_media_id_seq", allocationSize = 1) 
    @Column(name = "media_id")
    protected Long mediaId;

    @Column(name = "title", nullable = false)
    protected String title;

    @Column(name = "is_animation", nullable = false)
    protected Boolean isAnimation;

    @Type(type = "jsonb")
    @Column(name = "genres", nullable = false)
    protected List<Genre> genres;

    @Column(name = "director", nullable = false)
    protected String director;

    @Column(name = "release_year", nullable = false)
    protected Integer releaseYear;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    protected String description;

    @Column(name = "age_rating", nullable = false)
    protected Integer ageRating;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT", nullable = false)
    protected String thumbnailUrl;

    @Column(name = "poster_url", columnDefinition = "TEXT", nullable = false)
    protected String posterUrl;

    @Column(name = "trailer_url", columnDefinition = "TEXT", nullable = false)
    protected String trailerUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt = LocalDateTime.now();

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
        
        Media otherMedia = (Media) obj;
        if (this.mediaId != null){
            return this.mediaId.equals(otherMedia.mediaId);
        } 
        if (otherMedia.mediaId != null){
            return otherMedia.mediaId.equals(this.mediaId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.mediaId.hashCode();
    }
}
