package com.example.NetflixClone.Media.DataProvider;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Media.Models.MediaList;

public interface MediaListRepository extends JpaRepository<MediaList, UUID>{
    
}
