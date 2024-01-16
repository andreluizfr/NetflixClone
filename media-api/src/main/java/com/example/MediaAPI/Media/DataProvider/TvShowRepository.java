package com.example.MediaAPI.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaAPI.Media.Models.TvShow;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {

}
