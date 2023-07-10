package com.example.admin.Models;

import com.example.admin.Models.enums.Genre;

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
import jakarta.persistence.Table;

@Entity(name = "Media")
@Table(name = "Media")
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToMany(mappedBy = "medias")
    private Set<MediaList> lists;

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

    @Column(name = "descriptions", nullable = false)
    private String descriptions;

    @Column(name = "age_rating", nullable = false)
    private int ageRating;

    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;
    
    @Column(name = "thumbnail_blur_hash", nullable = false)
    private String thumbnailBlurHash;

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
        String thumbnailBlurHash
    ) {

        this.title = title;
        this.isAnimation = isAnimation;
        this.genres = genres;
        this.director = director;
        this.releaseYear = releaseYear;
        this.descriptions = descriptions;
        this.ageRating = ageRating;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailBlurHash = thumbnailBlurHash;
        this.createdAt = LocalDateTime.now();
    }
    
    public Media(Long id, String title, boolean isAnimation, List<Genre> genres, String director, int releaseYear,
			String descriptions, int ageRating, String thumbnailUrl, String thumbnailBlurHash, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.isAnimation = isAnimation;
		this.genres = genres;
		this.director = director;
		this.releaseYear = releaseYear;
		this.descriptions = descriptions;
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
	
	public Long getId() {
        return this.id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setLists(Set<MediaList> lists) {
        this.lists = lists;
    }
    public Set<MediaList> getLists() {
        return this.lists;
    }
    public void setIsAnimation(boolean isAnimation) {
        this.isAnimation = isAnimation;
    }
    public boolean getIsAnimation() {
        return this.isAnimation;
    }
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
    public List<Genre> getGenres() {
        return this.genres;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getDirector() {
        return this.director;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public int getReleaseYear() {
        return this.releaseYear;
    }
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    public String getDescriptions() {
        return this.descriptions;
    }
    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }
    public int getAgeRating() {
        return this.ageRating;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }
    public void setThumbnailBlurHash(String thumbnailBlurHash) {
        this.thumbnailBlurHash = thumbnailBlurHash;
    }
    public String getThumbnailBlurHash() {
        return this.thumbnailBlurHash;
    }

}


