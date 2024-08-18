package com.example.MediaAPI.Media.Business;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MediaAPI.Media.Business.ObjectStorageService.Impl.AmazonStorageService;
import com.example.MediaAPI.Media.DataProvider.AnimeRepository;
import com.example.MediaAPI.Media.DataProvider.EpisodeTrackRepository;
import com.example.MediaAPI.Media.DataProvider.MediaListRepository;
import com.example.MediaAPI.Media.DataProvider.MediaRepository;
import com.example.MediaAPI.Media.DataProvider.MovieRepository;
import com.example.MediaAPI.Media.DataProvider.PreviewMediaRepository;
import com.example.MediaAPI.Media.DataProvider.TvShowRepository;
import com.example.MediaAPI.Media.Exceptions.MediaNotFoundException;
import com.example.MediaAPI.Media.Exceptions.PreviewMediaNotFoundException;
import com.example.MediaAPI.Media.Models.Anime;
import com.example.MediaAPI.Media.Models.EpisodeTrack;
import com.example.MediaAPI.Media.Models.CustomExclusionStrategy;
import com.example.MediaAPI.Media.Models.Media;
import com.example.MediaAPI.Media.Models.MediaList;
import com.example.MediaAPI.Media.Models.Movie;
import com.example.MediaAPI.Media.Models.PreviewMedia;
import com.example.MediaAPI.Media.Models.TvShow;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Transactional
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
    EpisodeTrackRepository episodeTrackRepository;

    @Autowired
    VideoProcessingPipelineBusiness videoProcessingPipelineBusiness;

    @Autowired
    AmazonStorageService amazonStorageService;

    Gson gson = new GsonBuilder()
            .setExclusionStrategies(new CustomExclusionStrategy())
            .create();

    private static final Logger logger = LogManager.getLogger(MediaBusiness.class);

    @Cacheable(cacheNames = "largeTimeCache")
    public List<Media> getAllMedias() {

        List<Media> mediasList = mediaRepository.findAll();

        return mediasList;
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public SimpleEntry<String, Class<? extends Media>> getMedia(Long id) throws MediaNotFoundException {

        Media media = mediaRepository
                .findById(id)
                .orElseThrow(() -> new MediaNotFoundException("Nenhuma mídia com esse id foi encontrada."));

        Class<? extends Media> classz = Media.class;

        if(media instanceof Movie) {
            classz = Movie.class;
        } else if(media instanceof TvShow) {
            classz = TvShow.class;
        } else if(media instanceof Anime) {
            classz = Anime.class;
        } 

        return new SimpleEntry<String, Class<? extends Media>>(gson.toJson(media), classz);
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public List<Movie> getAllMovies() {

        return movieRepository.findAll();
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public List<TvShow> getAllTvShows() {

        return tvShowRepository.findAll();
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public List<Anime> getAllAnimes() {

        return animeRepository.findAll();
    }

    @Cacheable(cacheNames = "mediumTimeCache")
    public PreviewMedia getCurrentPreviewMedia() throws PreviewMediaNotFoundException {

        return previewMediaRepository
                .findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new PreviewMediaNotFoundException("Nenhuma preview encontrada."));
    }

    @Cacheable(cacheNames = "largeTimeCache")
    public List<MediaList> getAllMediaLists() {

        return MediaListRepository.findAll();
    }

    public Path resolveMediaUploadDirectory(String trackId) {
        return Paths.get(System.getProperty("user.dir"))
                .resolve("src")
                .resolve("main")
                .resolve("resources")
                .resolve("tmp")
                .resolve("streamingTracks")
                .resolve(trackId);
    }

    @Cacheable(cacheNames = "mediumTimeCache")
    public String getEpisodeTrack(UUID id) {

        EpisodeTrack episodeTrack = episodeTrackRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum episódio com esse id foi encontrado."));

        return gson.toJson(episodeTrack);
    }

    public void uploadEpisodeTrack(MultipartFile file, Media media, EpisodeTrack episodeTrack)
            throws IOException, MediaNotFoundException {

        EpisodeTrack episodeTrackToCreate = new EpisodeTrack();
        episodeTrackToCreate.setDuration(episodeTrack.getDuration());
        episodeTrackToCreate.setOrder(episodeTrack.getOrder());
        episodeTrackToCreate.setSeason(episodeTrack.getSeason());
        episodeTrackToCreate.setTitle(episodeTrack.getTitle());
        EpisodeTrack episodeTrackCreated = episodeTrackRepository.save(episodeTrack);

        if (media instanceof TvShow) {
            TvShow outdatedTvShow = tvShowRepository.findById(media.getMediaId())
                    .orElseThrow(() -> new MediaNotFoundException(
                            "O id " + media.getMediaId() + " não pertence a nenhuma série registrada no sistema."));
            outdatedTvShow.getEpisodeTracks().add(episodeTrackCreated);
            tvShowRepository.save(outdatedTvShow);
        } else if (media instanceof Anime) {
            Anime outdatedAnime = animeRepository.findById(media.getMediaId())
                    .orElseThrow(() -> new MediaNotFoundException(
                            "O id " + media.getMediaId() + " não pertence a nenhuma série registrada no sistema."));
            outdatedAnime.getEpisodeTracks().add(episodeTrackCreated);
            animeRepository.save(outdatedAnime);
        } else {
            throw new IllegalArgumentException("Media incompatível.");
        }

        Path episodeDirectory = this.resolveMediaUploadDirectory(episodeTrackCreated.getId().toString());

        Files.createDirectories(episodeDirectory);
        logger.info("Diretório criado: " + episodeDirectory.toAbsolutePath().toString());

        Path filePath = episodeDirectory.resolve(file.getName());
        file.transferTo(filePath);

        videoProcessingPipelineBusiness.addToQueue(episodeTrackCreated, episodeDirectory.toString(), file.getName());
    }

    public void updateEpisodeTrack(EpisodeTrack episodeTrack) {
        episodeTrackRepository.save(episodeTrack);
    }

    public void getMediaSignedCookie(HttpServletResponse response)
            throws MalformedURLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        amazonStorageService.addMediaAccessCookieToResponse(response);
    }
}
