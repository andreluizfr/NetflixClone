package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.Genre;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "TvShow")
//@Table(name = "TvShow")
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

}
