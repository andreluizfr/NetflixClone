package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.Genre;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "TvShow")
@Table(name = "TvShow")
public class TvShow extends Media{

    @Column(name = "number_of_seasons")
    private int numberOfSeasons;

    @Column(name = "season_number")
    private int seasonNumber;

    @Column(name = "actors_actresses")
    private List<String> actorsActresses;

    TvShow(
        String title,
        boolean isAnimated,
        ArrayList<Genre> genres,
        String director,
        int releaseYear,

        int numberOfSeasons,
        int seasonNumber,
        ArrayList<String> actorsActresses
    ) {
        super(title, isAnimated, genres, director, releaseYear);

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
