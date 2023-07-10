package com.example.NetflixClone.Repositories;

import java.util.UUID;

import com.example.NetflixClone.Models.MediaList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaListRepositoryDAO extends JpaRepository<MediaList, UUID>{
    
}
