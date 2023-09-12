package com.example.NetflixClone.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Media.Models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
