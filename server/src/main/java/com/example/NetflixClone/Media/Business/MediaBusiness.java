package com.example.NetflixClone.Media.Business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.NetflixClone.Exceptions.MediaNotFoundException;
import com.example.NetflixClone.Exceptions.PreviewMediaNotFoundException;
import com.example.NetflixClone.Media.DataProvider.AnimeRepository;
import com.example.NetflixClone.Media.DataProvider.MediaListRepository;
import com.example.NetflixClone.Media.DataProvider.MediaRepository;
import com.example.NetflixClone.Media.DataProvider.MovieRepository;
import com.example.NetflixClone.Media.DataProvider.PreviewMediaRepository;
import com.example.NetflixClone.Media.DataProvider.TvShowRepository;
import com.example.NetflixClone.Media.Models.Anime;
import com.example.NetflixClone.Media.Models.Media;
import com.example.NetflixClone.Media.Models.MediaList;
import com.example.NetflixClone.Media.Models.Movie;
import com.example.NetflixClone.Media.Models.PreviewMedia;
import com.example.NetflixClone.Media.Models.TvShow;

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

    @Transactional
    public List<MediaList> getAllMediaLists() {

        List<MediaList> mediasLists = MediaListRepository.findAll();

        return mediasLists;
    }

    @Transactional
    public List<Media> getAllMedias() {

        List<Media> mediasList = mediaRepository.findAll();

        return mediasList;
    }

    @Transactional
    public Media getMedia(Long id) throws MediaNotFoundException{

        Optional<Media> media = mediaRepository.findById(id);

        if(media.isPresent())
            return media.get();
        else 
            throw new MediaNotFoundException("Nenhuma mídia com esse id foi encontrada.");
    }

    @Transactional
    public PreviewMedia getCurrentPreviewMedia() throws PreviewMediaNotFoundException {

        Optional<PreviewMedia> previewMedia = previewMediaRepository.findTopByOrderByCreatedAtDesc();

        if (previewMedia.isPresent())
            return previewMedia.get();
        else
            throw new PreviewMediaNotFoundException("Nenhuma preview encontrada.");
    }

    @Transactional
    public List<Movie> getAllMovies() {
        List<Movie> moviesList = movieRepository.findAll();

        return moviesList;
    }

    @Transactional
    public List<TvShow> getAllTvShows() {
        List<TvShow> tvShowsList = tvShowRepository.findAll();

        return tvShowsList;
    }

    @Transactional
    public List<Anime> getAllAnimes() {
        List<Anime> animesList = animeRepository.findAll();

        return animesList;
    }
}
