package com.example.MediaService.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaService.Media.Models.TvShow;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {

}
