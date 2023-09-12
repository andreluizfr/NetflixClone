package com.example.admin.Models;

import com.example.admin.Models.enums.Genre;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "TvShow")
@Table(name = "TvShow")
public class TvShow extends Media{

    @Column(name = "number_of_seasons", nullable = false)
    private int numberOfSeasons;

    @Column(name = "season_number", nullable = false)
    private int seasonNumber;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "actors_actresses", nullable = false)
    private List<String> actorsActresses;

    @OneToMany(mappedBy = "tvShowId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episode> episodes;

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
        String posterUrl,
        String trailerUrl,

        int numberOfSeasons,
        int seasonNumber,
        List<String> actorsActresses,
        List<Episode> episodes
    ) {
        super(title, isAnimation, genres, director, releaseYear, descriptions, ageRating, thumbnailUrl, posterUrl, trailerUrl);

        this.numberOfSeasons = numberOfSeasons;
        this.seasonNumber = seasonNumber;
        this.actorsActresses = actorsActresses;
        this.episodes = episodes;
    }

    @Override
    public boolean equals(Object arg0) {
        TvShow otherTvShow = (TvShow) arg0;
        return this.mediaId.equals(otherTvShow.mediaId);
    }

    @Override
    public int hashCode() {
        return this.mediaId.hashCode();
    }

}
