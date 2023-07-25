package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.Genre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Media")
@Table(name = "Media")
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    protected Long mediaId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "is_animation", nullable = false)
    private boolean isAnimation;

    @Column(name = "genres", nullable = false)
    private List<Genre> genres;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @Column(name = "descriptions", columnDefinition = "TEXT", nullable = false)
    private String descriptions;

    @Column(name = "age_rating", nullable = false)
    private int ageRating;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT", nullable = false)
    private String thumbnailUrl;
    
    @Column(name = "poster_url", columnDefinition = "TEXT", nullable = false)
    private String posterUrl;

    @Column(name = "trailer_url", columnDefinition = "TEXT", nullable = false)
    private String trailerUrl;

    @ManyToMany(mappedBy = "medias")
    private Set<MediaList> mediaLists;

    @JsonBackReference
    @OneToOne(mappedBy = "media")
    private PreviewMedia previewMedia;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Media() {
    	this.createdAt = LocalDateTime.now();
    }

    public Media(
        String title,
        boolean isAnimation,
        List<Genre> genres,
        String director,
        int releaseYear,
        String descriptions,
        int ageRating,
        String thumbnailUrl,
        String posterUrl,
        String trailerUrl
    ) {

        this.title = title;
        this.isAnimation = isAnimation;
        this.genres = genres;
        this.director = director;
        this.releaseYear = releaseYear;
        this.descriptions = descriptions;
        this.ageRating = ageRating;
        this.thumbnailUrl = thumbnailUrl;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object arg0) {
        Media otherMedia = (Media) arg0;
        return this.mediaId.equals(otherMedia.mediaId);
    }

    @Override
    public int hashCode() {
        return this.mediaId.hashCode();
    }

}
