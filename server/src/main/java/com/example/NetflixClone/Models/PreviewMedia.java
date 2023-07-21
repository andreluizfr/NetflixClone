package com.example.NetflixClone.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "PreviewMedia")
@Table(name = "PreviewMedia")
public class PreviewMedia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "previewMedia", nullable = false)
	Media previewMedia;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public PreviewMedia(Media previewMedia) {
        this.previewMedia = previewMedia;
        this.createdAt = LocalDateTime.now();
    }
}
