package com.example.NetflixClone.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Media.Models.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
