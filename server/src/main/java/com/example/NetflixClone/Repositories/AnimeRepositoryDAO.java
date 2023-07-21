package com.example.NetflixClone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Models.Anime;

public interface AnimeRepositoryDAO extends JpaRepository<Anime, Long> {

}
