package com.example.NetflixClone.Models;

import com.example.NetflixClone.Models.enums.ShowType;
import com.example.NetflixClone.Models.enums.Genre;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Show")
@Table(name = "Show")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "showType")
    private ShowType showType;

    @Column(name = "isMovieSeries")
    private boolean isMovieSeries;

    @Column(name = "sequenceNumber")
    private int sequenceNumber;

    @Column(name = "hasSeasons")
    private boolean hasSeasons;

    @Column(name = "seasonNumber")
    private int seasonNumber;

    @Column(name = "genres")
    private List<Genre> genres;

    @Column(name = "director")
    private String director;

    @Column(name = "actorsActresses")
    private List<String> actorsActresses;

    @Column(name = "releaseYear")
    private int releaseYear;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    Show(
            String title,
            ShowType showType,
            boolean isMovieSeries,
            int sequenceNumber,
            boolean hasSeasons,
            int seasonNumber,
            ArrayList<Genre> genres,
            String director,
            ArrayList<String> actorsActresses,
            int releaseYear) {

        this.title = title;
        this.showType = showType;
        this.isMovieSeries = isMovieSeries;
        this.sequenceNumber = sequenceNumber; // receber 1
        this.hasSeasons = hasSeasons;
        this.seasonNumber = seasonNumber; // receber 1
        this.genres = genres;
        this.director = director;
        this.actorsActresses = actorsActresses;
        this.releaseYear = releaseYear;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object arg0) {
        Show otherShow = (Show) arg0;
        return this.id.equals(otherShow.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
