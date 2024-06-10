package com.example.MediaAPI.Media.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PreviewMedia")
@Table(name = "previewMedia", indexes = {
    @Index(columnList = "created_at", name = "ix_preview_media_created_at")
})
public class PreviewMedia implements Serializable {

    private static final long serialVersionUID = 2539650966267757690L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="preview_media_id_seq_gen")
    @SequenceGenerator(name = "preview_media_id_seq_gen", sequenceName = "preview_media_id_seq", allocationSize = 1) 
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "media_id", referencedColumnName = "media_id")
    private Media media;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Version
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false) 
    private LocalDateTime updatedAt = LocalDateTime.now();
    
}
