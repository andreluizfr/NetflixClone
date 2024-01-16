package com.example.MediaAPI.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaAPI.Media.Models.Media;

public interface MediaRepository extends JpaRepository<Media, Long>{
    
}
