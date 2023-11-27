package com.example.MediaService.Media.Business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.MediaService.Media.DataProvider.AnimeRepository;
import com.example.MediaService.Media.DataProvider.MediaListRepository;
import com.example.MediaService.Media.DataProvider.MediaRepository;
import com.example.MediaService.Media.DataProvider.MovieRepository;
import com.example.MediaService.Media.DataProvider.PreviewMediaRepository;
import com.example.MediaService.Media.DataProvider.TvShowRepository;
import com.example.MediaService.Media.Exceptions.MediaNotFoundException;
import com.example.MediaService.Media.Exceptions.PreviewMediaNotFoundException;
import com.example.MediaService.Media.Models.Anime;
import com.example.MediaService.Media.Models.Media;
import com.example.MediaService.Media.Models.MediaList;
import com.example.MediaService.Media.Models.Movie;
import com.example.MediaService.Media.Models.PreviewMedia;
import com.example.MediaService.Media.Models.TvShow;

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

    public static final String MEDIAS = "medias";

    @Cacheable(value = MEDIAS)
    public List<Media> getAllMedias() {

        List<Media> mediasList = mediaRepository.findAll();

        return mediasList;
    }

    @Cacheable(value = MEDIAS)
    public Media getMedia(Long id) 
            throws MediaNotFoundException{

        Optional<Media> media = mediaRepository.findById(id);

        if(media.isPresent())
            return media.get();
        else 
            throw new MediaNotFoundException("Nenhuma mídia com esse id foi encontrada.");
    }

    @Cacheable(value = MEDIAS)
    public List<Movie> getAllMovies() {
        List<Movie> moviesList = movieRepository.findAll();

        return moviesList;
    }

    @Cacheable(value = MEDIAS)
    public List<TvShow> getAllTvShows() {
        List<TvShow> tvShowsList = tvShowRepository.findAll();

        return tvShowsList;
    }

    @Cacheable(value = MEDIAS)
    public List<Anime> getAllAnimes() {
        List<Anime> animesList = animeRepository.findAll();

        return animesList;
    }

    @Cacheable(value = MEDIAS)
    public PreviewMedia getCurrentPreviewMedia() 
            throws PreviewMediaNotFoundException {

        Optional<PreviewMedia> previewMedia = previewMediaRepository.findTopByOrderByCreatedAtDesc();

        if (previewMedia.isPresent())
            return previewMedia.get();
        else
            throw new PreviewMediaNotFoundException("Nenhuma preview encontrada.");
    }

    @Cacheable(value = MEDIAS)
    public List<MediaList> getAllMediaLists() {

        List<MediaList> mediasLists = MediaListRepository.findAll();

        return mediasLists;
    }
}
