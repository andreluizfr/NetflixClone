package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.Genre;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Anime")
//@Table(name = "Anime")
public class Anime extends Media{

    @Column(name = "number_of_seasons", nullable = false)
    private int numberOfSeasons;

    @Column(name = "season_number", nullable = false)
    private int seasonNumber;

    @Column(name = "studio", nullable = false)
    private String studio;

    public Anime() {super();}

    public Anime(
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
        String studio
    ) {
        super(title, isAnimation, genres, director, releaseYear, descriptions, ageRating, thumbnailUrl, thumbnailBlurHash);

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
