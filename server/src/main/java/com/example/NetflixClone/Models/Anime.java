package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.Genre;

import java.util.List;

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

    public Anime(
        String title,
        boolean isAnimation,
        List<Genre> genres,
        String director,
        int releaseYear,
        String description,
        int ageRating,
        String thumbnailUrl,

        int numberOfSeasons,
        int seasonNumber,
        String studio
    ) {
        super(title, isAnimation, genres, director, releaseYear, description, ageRating, thumbnailUrl);

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
