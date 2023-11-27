package com.example.MediaService.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaService.Media.Models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
