package com.example.admin.Models;

import com.example.admin.Models.enums.Genre;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity(name = "Movie")
@Table(name = "Movie")
public class Movie extends Media{

    @Column(name = "is_movie_series")
    private boolean isMovieSeries;

    @Column(name = "sequence_number")
    private int sequenceNumber;

    @Column(name = "actors_actresses")
    private List<String> actorsActresses;

    Movie(
        String title,
        boolean isAnimated,
        List<Genre> genres,
        String director,
        int releaseYear,

        boolean isMovieSeries,
        int sequenceNumber,
        List<String> actorsActresses
    ) {
        super(title, isAnimated, genres, director, releaseYear);

        this.isMovieSeries = isMovieSeries;
        this.sequenceNumber = sequenceNumber; // receber 1 se nao for
        this.actorsActresses = actorsActresses;
    }
    
    Movie() {}

    @Override
    public boolean equals(Object arg0) {
        Movie otherMovie = (Movie) arg0;
        return this.id.equals(otherMovie.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
