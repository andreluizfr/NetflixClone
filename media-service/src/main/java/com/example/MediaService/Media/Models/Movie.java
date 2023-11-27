package com.example.MediaService.Media.Models;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.envers.Audited;
import org.hibernate.type.SqlTypes;

import com.example.MediaService.Media.Models.Enums.Genre;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity(name = "Movie")
@Table(name = "Movie")
public class Movie extends Media {

    @Column(name = "is_movie_series", nullable = false)
    private boolean isMovieSeries;

    @Column(name = "sequence_number", nullable = false)
    private int sequenceNumber;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "actors_actresses", nullable = false)
    private List<String> actorsActresses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "episode_id", referencedColumnName = "id")
    private Episode episode;

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
            Episode episode) {
        super(title, isAnimation, genres, director, releaseYear, descriptions, ageRating, thumbnailUrl, posterUrl,
                trailerUrl);

        this.isMovieSeries = isMovieSeries;
        this.sequenceNumber = sequenceNumber; // receber 1 se nao for
        this.actorsActresses = actorsActresses;
        this.episode = episode;
    }
}
