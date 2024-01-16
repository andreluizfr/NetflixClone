package com.example.MediaAPI.Media.DataProvider;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MediaAPI.Media.Models.PreviewMedia;

public interface PreviewMediaRepository extends JpaRepository<PreviewMedia, Long>{
    Optional<PreviewMedia> findTopByOrderByCreatedAtDesc();
}
