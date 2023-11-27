package com.example.MediaService.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaService.Media.Models.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
