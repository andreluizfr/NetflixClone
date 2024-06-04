package com.example.MediaAPI.Media.Models;

import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TvShow")
@Table(name = "tvShow")
@TypeDefs({
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class TvShow extends Media {

    @Column(name = "number_of_seasons", nullable = false)
    private Integer numberOfSeasons;

    @Column(name = "season_number", nullable = false)
    private Integer seasonNumber;

    @Type(type = "jsonb")
    @Column(name = "actors_actresses", nullable = false)
    private List<String> actorsActresses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "media_id")
    protected List<EpisodeTrack> episodeTracks = new ArrayList<EpisodeTrack>();

}
