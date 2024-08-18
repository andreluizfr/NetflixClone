package com.example.MediaAPI.Media.Models;
import java.util.List;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
//import org.hibernate.envers.Audited;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Movie")
@Table(name = "movie")
@TypeDefs({
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Movie extends Media {
    
    @Column(name = "movie_series_flag", nullable = false)
    private Boolean isMovieSeries;
    
    @Column(name = "sequence_number", nullable = false)
    private Integer sequenceNumber;
    
    @Type(type = "jsonb")
    @Column(name = "actors_actresses", nullable = false)
    private List<String> actorsActresses;

    @OneToOne(mappedBy = "movie", fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.REFRESH })
    protected EpisodeTrack episodeTrack;

}
