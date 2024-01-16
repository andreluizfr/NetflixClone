package com.example.MediaAPI.Media.DataProvider;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaAPI.Media.Models.MediaList;

public interface MediaListRepository extends JpaRepository<MediaList, UUID>{
    
}
