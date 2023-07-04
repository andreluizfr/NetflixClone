package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.Genre;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Anime")
@Table(name = "Anime")
public class Anime extends Media{

    @Column(name = "number_of_seasons")
    private int numberOfSeasons;

    @Column(name = "season_number")
    private int seasonNumber;

    @Column(name = "studio")
    private String studio;

    Anime(
        String title,
        boolean isAnimated,
        ArrayList<Genre> genres,
        String director,
        int releaseYear,

        int numberOfSeasons,
        int seasonNumber,
        String studio
    ) {
        super(title, isAnimated, genres, director, releaseYear);

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

}
