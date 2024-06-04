package com.example.MediaAPI.Media.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EpisodeTrack")
@Table(name = "episode_track")
public class EpisodeTrack extends TrackMetadata implements Serializable {

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "duration", nullable = true) // in minutes
    private int duration;

    @Column(name = "n_order", nullable = true)
    private Integer order;

    @Column(name = "season", nullable = true)
    private Integer season;
}
