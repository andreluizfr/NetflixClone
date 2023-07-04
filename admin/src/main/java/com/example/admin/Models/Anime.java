package com.example.admin.Models;

import com.example.admin.Models.enums.Genre;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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
    
    Anime() {}

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
