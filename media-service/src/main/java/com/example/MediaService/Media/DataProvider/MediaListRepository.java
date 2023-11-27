package com.example.MediaService.Media.DataProvider;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaService.Media.Models.MediaList;

public interface MediaListRepository extends JpaRepository<MediaList, UUID>{
    
}
