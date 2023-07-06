package com.example.admin.Models;

import com.example.admin.Models.enums.Genre;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity(name = "Media")
@Table(name = "Media")
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

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

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "age_rating", nullable = false)
    private int ageRating;

    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;
    
    @Column(name = "thumbnail_blur_hash", nullable = false)
    private String thumbnailBlurHash;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Media(
        String title,
        boolean isAnimation,
        List<Genre> genres,
        String director,
        int releaseYear,
        String description,
        int ageRating,
        String thumbnailUrl,
        String thumbnailBlurHash
    ) {

        this.title = title;
        this.isAnimation = isAnimation;
        this.genres = genres;
        this.director = director;
        this.releaseYear = releaseYear;
        this.description = description;
        this.ageRating = ageRating;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailBlurHash = thumbnailBlurHash;
        this.createdAt = LocalDateTime.now();
    }
    
    public Media(Long id, String title, boolean isAnimation, List<Genre> genres, String director, int releaseYear,
			String description, int ageRating, String thumbnailUrl, String thumbnailBlurHash, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.isAnimation = isAnimation;
		this.genres = genres;
		this.director = director;
		this.releaseYear = releaseYear;
		this.description = description;
		this.ageRating = ageRating;
		this.thumbnailUrl = thumbnailUrl;
		this.thumbnailBlurHash = thumbnailBlurHash;
		this.createdAt = createdAt;
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


