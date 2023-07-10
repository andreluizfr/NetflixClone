package com.example.admin.Models;

import com.example.admin.Models.enums.Genre;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "TvShow")
@Table(name = "TvShow")
public class TvShow extends Media{

    @Column(name = "number_of_seasons", nullable = false)
    private int numberOfSeasons;

    @Column(name = "season_number", nullable = false)
    private int seasonNumber;

    @Column(name = "actors_actresses", nullable = false)
    private List<String> actorsActresses;

    public TvShow() {super();}
    
    public TvShow(
        String title,
        boolean isAnimation,
        List<Genre> genres,
        String director,
        int releaseYear,
        String descriptions,
        int ageRating,
        String thumbnailUrl,
        String thumbnailBlurHash,

        int numberOfSeasons,
        int seasonNumber,
        List<String> actorsActresses
    ) {
        super(title, isAnimation, genres, director, releaseYear, descriptions, ageRating, thumbnailUrl, thumbnailBlurHash);

        this.numberOfSeasons = numberOfSeasons;
        this.seasonNumber = seasonNumber;
        this.actorsActresses = actorsActresses;
    }

    @Override
    public boolean equals(Object arg0) {
        TvShow otherTvShow = (TvShow) arg0;
        return this.id.equals(otherTvShow.id);
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
    public void setActorsActresses(List<String> actorsActresses) {
        this.actorsActresses = actorsActresses;
    }
    public List<String> getActorsActresses() {
        return this.actorsActresses;
    }
}

