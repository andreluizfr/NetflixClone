package com.example.NetflixClone.Media.DataProvider;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.NetflixClone.Media.Models.PreviewMedia;

public interface PreviewMediaRepository extends JpaRepository<PreviewMedia, Long>{
    Optional<PreviewMedia> findTopByOrderByCreatedAtDesc();
}
