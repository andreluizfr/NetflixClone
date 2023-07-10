package com.example.NetflixClone.Models;

import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "medias")
    private Set<Media> medias;

    public MediaList() {super();}

    public MediaList(String title, Set<Media> medias) {
        this.title = title;
        this.medias = medias;
    }

}
