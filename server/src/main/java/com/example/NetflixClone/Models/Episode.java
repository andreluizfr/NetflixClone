package com.example.NetflixClone.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Episode")
@Table(name = "Episode")
public class Episode {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "tv_show_id")
    private TvShow tvShow;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    @Column(name = "title", nullable = true)
    String title;

    @Column(name = "thumbnail_url", nullable = true)
    String thumbnailUrl;

    @Column(name = "episode_url", nullable = false)
    String episodeUrl;

    @Column(name = "duration", nullable = false) //in minutes
    int duration;

    @Column(name = "order", nullable = true)
    int order;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    //para series e animes
    public Episode(String title, String thumbnailUrl, String episodeUrl, int duration, int order) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.episodeUrl = episodeUrl;
        this.duration = duration;
        this.order = order;
        this.createdAt = LocalDateTime.now();
    }

    //para filmes
    public Episode(String episodeUrl, int duration) {
        this.episodeUrl = episodeUrl;
        this.duration = duration;
        this.createdAt = LocalDateTime.now();
    }
}
