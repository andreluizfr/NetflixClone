package com.example.MediaService.Media.DataProvider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaService.Media.Models.Media;

public interface MediaRepository extends JpaRepository<Media, Long>{
    
}
