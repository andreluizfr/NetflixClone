package com.example.MediaService.Media.DataProvider;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaService.Media.Models.PreviewMedia;

public interface PreviewMediaRepository extends JpaRepository<PreviewMedia, Long>{
    Optional<PreviewMedia> findTopByOrderByCreatedAtDesc();
}
