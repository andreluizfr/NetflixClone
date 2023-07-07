package com.example.admin.Models;

import com.example.admin.Models.enums.Genre;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "Anime")
@Table(name = "Anime")
public class Anime extends Media{

    @Column(name = "number_of_seasons", nullable = false)
    private int numberOfSeasons;

    @Column(name = "season_number", nullable = false)
    private int seasonNumber;

    @Column(name = "studio", nullable = false)
    private String studio;

    public Anime(
        String title,
        boolean isAnimation,
        List<Genre> genres,
        String director,
        int releaseYear,
        String description,
        int ageRating,
        String thumbnailUrl,
        String thumbnailBlurHash,

        int numberOfSeasons,
        int seasonNumber,
        String studio
    ) {
        super(title, isAnimation, genres, director, releaseYear, description, ageRating, thumbnailUrl, thumbnailBlurHash);

        this.numberOfSeasons = numberOfSeasons;
        this.seasonNumber = seasonNumber;
        this.studio = studio;
    }

    @Override
    public boolean equals(Object arg0) {
        Anime otherAnime = (Anime) arg0;
        return this.id.equals(otherAnime.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }
    public int getNumberOfSeasons() {
        return this.numberOfSeasons;
    }
    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    public int getSeasonNumber() {
        return this.seasonNumber;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }
    public String getStudio() {
        return this.studio;
    }

}
