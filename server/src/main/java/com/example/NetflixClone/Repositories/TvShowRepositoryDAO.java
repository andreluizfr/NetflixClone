package com.example.NetflixClone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Models.TvShow;

public interface TvShowRepositoryDAO extends JpaRepository<TvShow, Long> {

}
