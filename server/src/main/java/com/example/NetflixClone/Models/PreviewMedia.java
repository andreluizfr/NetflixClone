package com.example.NetflixClone.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id", referencedColumnName = "media_id")
	private Media media;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public PreviewMedia(Media media) {
        this.media = media;
        this.createdAt = LocalDateTime.now();
    }
}
