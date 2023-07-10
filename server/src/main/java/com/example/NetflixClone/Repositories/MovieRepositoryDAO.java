package com.example.NetflixClone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Models.Movie;

public interface MovieRepositoryDAO extends JpaRepository<Movie, Long> {

}
