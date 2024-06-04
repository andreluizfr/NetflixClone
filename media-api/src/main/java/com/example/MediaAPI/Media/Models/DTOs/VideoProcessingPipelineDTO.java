package com.example.MediaAPI.Media.Models.DTOs;

import com.example.MediaAPI.Media.Models.EpisodeTrack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoProcessingPipelineDTO {
    private EpisodeTrack episodeTrack;
    private String trackDir;
    private String inputFileName;
}