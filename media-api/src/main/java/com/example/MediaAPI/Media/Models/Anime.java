package com.example.MediaAPI.Media.Models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Anime")
@Table(name = "Anime")
@TypeDefs({
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Anime extends Media {

    @Column(name = "number_of_seasons", nullable = false)
    private int numberOfSeasons;

    @Column(name = "season_number", nullable = false)
    private int seasonNumber;

    @Column(name = "studio", nullable = false)
    private String studio;

    @Type(type = "jsonb")
    @Column(name = "voice_actors_actresses", nullable = false)
    private List<String> voiceActorsActresses = new ArrayList<>();
    
}
