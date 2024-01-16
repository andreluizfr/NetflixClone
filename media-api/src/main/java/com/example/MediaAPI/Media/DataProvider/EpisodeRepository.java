package com.example.MediaAPI.Media.DataProvider;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaAPI.Media.Models.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, UUID> {

}
