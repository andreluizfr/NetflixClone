package com.example.admin.Models;

import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "MediaList")
@Table(name = "MediaList")
public class MediaList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    String title;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name= "medias", nullable = false)
    private Set<Media> medias;

    public MediaList() {}

    public MediaList(String title, Set<Media> medias) {
        this.title = title;
        this.medias = medias;
    }

    public MediaList(UUID id, String title, Set<Media> medias) {
        this.id = id;
        this.title = title;
        this.medias = medias;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setMedias(Set<Media> medias) {
        this.medias = medias;
    }
    public Set<Media> getMedias() {
        return this.medias;
    }
}
