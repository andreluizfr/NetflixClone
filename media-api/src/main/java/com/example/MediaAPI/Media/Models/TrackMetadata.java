package com.example.MediaAPI.Media.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.MediaAPI.Media.Models.Enums.TrackProcessingStatus;
import com.example.MediaAPI.Media.Models.Enums.TrackProcessingStatusConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TrackMetadata")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TrackMetadata implements Serializable {

    private static final long serialVersionUID = 2335650964267757670L;

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    protected UUID id;

    @Convert(converter = TrackProcessingStatusConverter.class)
    @Column(name = "processing_status", nullable = false,  columnDefinition = "SMALLINT")
    private TrackProcessingStatus processingStatus = TrackProcessingStatus.NOT_PROCESSED;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Version
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false) 
    protected LocalDateTime updatedAt;

}
