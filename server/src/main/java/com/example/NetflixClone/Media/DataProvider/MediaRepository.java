package com.example.NetflixClone.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Media.Models.Media;

public interface MediaRepository extends JpaRepository<Media, Long>{
    
}
