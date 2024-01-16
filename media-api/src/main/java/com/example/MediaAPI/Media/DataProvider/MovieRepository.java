package com.example.MediaAPI.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaAPI.Media.Models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
