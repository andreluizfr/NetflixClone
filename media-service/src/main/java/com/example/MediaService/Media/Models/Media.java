package com.example.MediaService.Media.Models;

import com.example.MediaService.Media.Models.Enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Media")
@Table(name = "Media")
@Inheritance(strategy = InheritanceType.JOINED)
public class Media implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    protected Long mediaId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "is_animation", nullable = false)
    private boolean isAnimation;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "genres", nullable = false)
    private List<Genre> genres;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "age_rating", nullable = false)
    private int ageRating;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT", nullable = false)
    private String thumbnailUrl;

    @Column(name = "poster_url", columnDefinition = "TEXT", nullable = false)
    private String posterUrl;

    @Column(name = "trailer_url", columnDefinition = "TEXT", nullable = false)
    private String trailerUrl;

    @JsonIgnore
    @OneToOne(mappedBy = "media")
    private PreviewMedia previewMedia;

    @JsonIgnore
    @ManyToMany(mappedBy = "medias")
    private Set<MediaList> mediaLists;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Version
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false) 
    private LocalDateTime updatedAt;

    public Media(
            String title,
            boolean isAnimation,
            List<Genre> genres,
            String director,
            int releaseYear,
            String description,
            int ageRating,
            String thumbnailUrl,
            String posterUrl,
            String trailerUrl) {
        this.title = title;
        this.isAnimation = isAnimation;
        this.genres = genres;
        this.director = director;
        this.releaseYear = releaseYear;
        this.description = description;
        this.ageRating = ageRating;
        this.thumbnailUrl = thumbnailUrl;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
    }

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
