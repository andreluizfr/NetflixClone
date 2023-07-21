package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.Genre;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Anime")
@Table(name = "Anime")
public class Anime extends Media {

    @Column(name = "number_of_seasons", nullable = false)
    private int numberOfSeasons;

    @Column(name = "season_number", nullable = false)
    private int seasonNumber;

    @Column(name = "studio", nullable = false)
    private String studio;

    @Column(name = "voice_actors_actresses", nullable = false)
    private List<String> voiceActorsActresses;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episode> episodes;

    public Anime() {
        super();
    }

    public Anime(
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
            String studio,
            List<String> voiceActorsActresses,
            List<Episode> episodes) {
        super(title, isAnimation, genres, director, releaseYear, descriptions, ageRating, thumbnailUrl, posterUrl,
                trailerUrl);

        this.numberOfSeasons = numberOfSeasons;
        this.seasonNumber = seasonNumber;
        this.studio = studio;
        this.voiceActorsActresses = voiceActorsActresses;
        this.episodes = episodes;
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
