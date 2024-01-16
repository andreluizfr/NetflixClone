package com.example.MediaAPI.Media.Business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.MediaAPI.Media.DataProvider.AnimeRepository;
import com.example.MediaAPI.Media.DataProvider.MediaListRepository;
import com.example.MediaAPI.Media.DataProvider.MediaRepository;
import com.example.MediaAPI.Media.DataProvider.MovieRepository;
import com.example.MediaAPI.Media.DataProvider.PreviewMediaRepository;
import com.example.MediaAPI.Media.DataProvider.TvShowRepository;
import com.example.MediaAPI.Media.Exceptions.MediaNotFoundException;
import com.example.MediaAPI.Media.Exceptions.PreviewMediaNotFoundException;
import com.example.MediaAPI.Media.Models.Anime;
import com.example.MediaAPI.Media.Models.Media;
import com.example.MediaAPI.Media.Models.MediaList;
import com.example.MediaAPI.Media.Models.Movie;
import com.example.MediaAPI.Media.Models.PreviewMedia;
import com.example.MediaAPI.Media.Models.TvShow;
import com.google.gson.Gson;

@Service
public class MediaBusiness {

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    TvShowRepository tvShowRepository;

    @Autowired
    MediaListRepository MediaListRepository;

    @Autowired
    PreviewMediaRepository previewMediaRepository;

    @Autowired
    Gson gson;

    @Cacheable(cacheNames = "largeTimeCache")
    public List<Media> getAllMedias() {

        List<Media> mediasList = mediaRepository.findAll();

        return mediasList;
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public String getMedia(Long id) 
            throws MediaNotFoundException{

        Optional<Media> optionalMedia = mediaRepository.findById(id);

        if(optionalMedia.isPresent()){
            return gson.toJson(optionalMedia.get());
        }

        throw new MediaNotFoundException("Nenhuma mídia com esse id foi encontrada.");
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public List<Movie> getAllMovies() {
        List<Movie> moviesList = movieRepository.findAll();

        return moviesList;
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public List<TvShow> getAllTvShows() {
        List<TvShow> tvShowsList = tvShowRepository.findAll();

        return tvShowsList;
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public List<Anime> getAllAnimes() {
        List<Anime> animesList = animeRepository.findAll();

        return animesList;
    }

    @Cacheable(cacheNames = "mediumTimeCache")
    public PreviewMedia getCurrentPreviewMedia() 
            throws PreviewMediaNotFoundException {

        Optional<PreviewMedia> previewMedia = previewMediaRepository.findTopByOrderByCreatedAtDesc();

        if (previewMedia.isPresent())
            return previewMedia.get();
        else
            throw new PreviewMediaNotFoundException("Nenhuma preview encontrada.");
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public List<MediaList> getAllMediaLists() {

        List<MediaList> mediasLists = MediaListRepository.findAll();

        return mediasLists;
    }
}
