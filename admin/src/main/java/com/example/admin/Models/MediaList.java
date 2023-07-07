package com.example.admin.Models;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;


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
    name = "media_list_media", 
    joinColumns = @JoinColumn(name = "media_list_id"), 
    inverseJoinColumns = @JoinColumn(name = "media_id"))
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
