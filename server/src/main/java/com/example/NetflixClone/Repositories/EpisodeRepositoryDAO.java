package com.example.NetflixClone.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Models.Episode;

public interface EpisodeRepositoryDAO extends JpaRepository<Episode, UUID> {

}
