package com.example.NetflixClone.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Models.PreviewMedia;

public interface PreviewMediaRepositoryDAO extends JpaRepository<PreviewMedia, Long>{
    Optional<PreviewMedia> findTopByOrderByCreatedAtDesc();
}
