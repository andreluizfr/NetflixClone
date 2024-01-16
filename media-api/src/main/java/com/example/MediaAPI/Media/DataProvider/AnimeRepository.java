package com.example.MediaAPI.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaAPI.Media.Models.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
