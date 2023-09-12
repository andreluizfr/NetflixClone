package com.example.admin.Models;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "MediaList")
@Table(name = "MediaList")
public class MediaList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    String title;

    @ManyToMany
    @JoinTable(
        name = "media_list_and_media", 
        joinColumns = @JoinColumn(name = "media_list_id"), 
        inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    private Set<Media> medias;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public MediaList() {
        this.createdAt = LocalDateTime.now();
    }

    public MediaList(String title, Set<Media> medias) {
        this.title = title;
        this.medias = medias;
        this.createdAt = LocalDateTime.now();
    }

}
