package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.Genre;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Movie")
@Table(name = "Movie")
public class Movie extends Media{

    @Column(name = "is_movie_series", nullable = false)
    private boolean isMovieSeries;

    @Column(name = "sequence_number", nullable = false)
    private int sequenceNumber;

    @Column(name = "actors_actresses", nullable = false)
    private List<String> actorsActresses;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Episode episode;

    public Movie() {super();}

    public Movie(
        String title,
        boolean isAnimation,
        List<Genre> genres,
        String director,
        int releaseYear,
        String descriptions,
        int ageRating,
        String thumbnailUrl,
        String posterUrl,
        String trailerUrl,

        boolean isMovieSeries,
        int sequenceNumber,
        List<String> actorsActresses,
        Episode episode
    ) {
        super(title, isAnimation, genres, director, releaseYear, descriptions, ageRating, thumbnailUrl, posterUrl, trailerUrl);

        this.isMovieSeries = isMovieSeries;
        this.sequenceNumber = sequenceNumber; // receber 1 se nao for
        this.actorsActresses = actorsActresses;
        this.episode = episode;
    }

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
