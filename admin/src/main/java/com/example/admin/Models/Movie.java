package com.example.admin.Models;

import com.example.admin.Models.enums.Genre;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity(name = "Movie")
@Table(name = "Movie")
public class Movie extends Media{

    @Column(name = "is_movie_series", nullable = false)
    private boolean isMovieSeries;

    @Column(name = "sequence_number", nullable = false)
    private int sequenceNumber;

    @Column(name = "actors_actresses", nullable = false)
    private List<String> actorsActresses;

    public Movie(
        String title,
        boolean isAnimation,
        List<Genre> genres,
        String director,
        int releaseYear,
        String description,
        int ageRating,
        String thumbnailUrl,
        String thumbnailBlurHash,

        boolean isMovieSeries,
        int sequenceNumber,
        List<String> actorsActresses
    ) {
        super(title, isAnimation, genres, director, releaseYear, description, ageRating, thumbnailUrl, thumbnailBlurHash);

        this.isMovieSeries = isMovieSeries;
        this.sequenceNumber = sequenceNumber; // receber 1 se nao for
        this.actorsActresses = actorsActresses;
    }

    @Override
    public boolean equals(Object arg0) {
        Movie otherMovie = (Movie) arg0;
        return this.id.equals(otherMovie.id);
    }

    @Override
    public int hashCode() {
    	if(this.id!=null)
    		return this.id.hashCode();
    	else return 1;
    }

}

