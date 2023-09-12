package com.example.NetflixClone.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Media.Models.TvShow;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {

}
