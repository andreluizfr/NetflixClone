package com.example.NetflixClone.Media.DataProvider;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Media.Models.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, UUID> {

}
