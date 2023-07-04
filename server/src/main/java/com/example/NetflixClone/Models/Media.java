package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.Genre;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Media")
@Table(name = "Media")
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "is_nimated")
    private boolean isAnimated;

    @Column(name = "genres")
    private List<Genre> genres;

    @Column(name = "director")
    private String director;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "age_rating")
    private int ageRating;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    Media(
        String title,
        boolean isAnimated,
        List<Genre> genres,
        String director,
        int releaseYear
    ) {

        this.title = title;
        this.isAnimated = isAnimated;
        this.genres = genres;
        this.director = director;
        this.releaseYear = releaseYear;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object arg0) {
        Media otherMedia = (Media) arg0;
        return this.id.equals(otherMedia.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
