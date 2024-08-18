package com.example.MediaAPI.Media.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.example.MediaAPI.Media.Business.MediaBusiness;
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
import com.example.MediaAPI.Util.ResponseHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/api")
public class MediaController {

    @Autowired
    MediaBusiness mediaBusiness;

    Gson gson = new GsonBuilder()
        .setExclusionStrategies(new CustomExclusionStrategy())
        .create();

    @GetMapping("/media")
    public ResponseEntity<Object> getAllMedias() {
        List<Media> mediasList = mediaBusiness.getAllMedias();

        return ResponseHandler.generateResponse("Lista de mídias buscada com sucesso.",
                HttpStatus.OK,
                mediasList);
    }

    @GetMapping(value = "/media", params = "id")
    public ResponseEntity<Object> getMedia(@RequestParam(name = "id", required = true) Long id) throws MediaNotFoundException {
        
        SimpleEntry<String, Class<? extends Media>> simpleEntry = mediaBusiness.getMedia(id);
        
        Media media = gson.fromJson(simpleEntry.getKey(), simpleEntry.getValue());

        return ResponseHandler.generateResponse("Mídia buscada com sucesso.",
                HttpStatus.OK,
                media);
    }

    @GetMapping("/movie")
    public ResponseEntity<Object> getAllMovies() {

        List<Movie> moviesList = mediaBusiness.getAllMovies();

        return ResponseHandler.generateResponse("Filmes buscados com sucesso.",
                HttpStatus.OK,
                moviesList);
    }

    @GetMapping("/tvShow")
    public ResponseEntity<Object> getAllTvShows() {

        List<TvShow> tvShowsList = mediaBusiness.getAllTvShows();

        return ResponseHandler.generateResponse("Séries buscadas com sucesso.",
                HttpStatus.OK,
                tvShowsList);
    }

    @GetMapping("/anime")
    public ResponseEntity<Object> getAllAnimes() {

        List<Anime> animesList = mediaBusiness.getAllAnimes();

        return ResponseHandler.generateResponse("Animes buscados com sucesso.",
                HttpStatus.OK,
                animesList);
    }

    @GetMapping("/previewMedia/getCurrent")
    public ResponseEntity<Object> getCurrentPreviewMedia() throws PreviewMediaNotFoundException {

        PreviewMedia previewMedia = mediaBusiness.getCurrentPreviewMedia();

        return ResponseHandler.generateResponse("Preview buscado com sucesso.",
                HttpStatus.OK,
                previewMedia);
    }

    @GetMapping("/mediaList")
    public ResponseEntity<Object> getAllMediaLists() {

        List<MediaList> mediaLists = mediaBusiness.getAllMediaLists();

        return ResponseHandler.generateResponse("Lista buscada com sucesso.",
                HttpStatus.OK,
                mediaLists);
    }

    @GetMapping(value = "/track/episodeTrack", params = "id")
    public ResponseEntity<Object> getEpisodeTrack(@RequestParam(name = "id", required = true) UUID id) {

        EpisodeTrack episodeTrack = gson.fromJson(mediaBusiness.getEpisodeTrack(id), EpisodeTrack.class);

        return ResponseHandler.generateResponse("Episódio buscado com sucesso.",
                HttpStatus.OK,
                episodeTrack);
    }

    @HystrixCommand(commandKey = "/uploadEpisodeTrack", fallbackMethod = "fallbackResponse", commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), // the method that in 30s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "100"), // received at least 100 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"), // and got 30% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000") // will pass 60s with circuit open to thefallbackMethod
    })
    @PostMapping("/track/uploadEpisodeTrack")
    public ResponseEntity<Object> uploadTvShowEpisodeTrack(@RequestParam("file") MultipartFile file,
            @RequestParam("media") Media media, @RequestParam("episodeTrack") EpisodeTrack episodeTrack) throws MediaNotFoundException, IOException {

        mediaBusiness.uploadEpisodeTrack(file, media, episodeTrack);

        return ResponseHandler.generateResponse("Arquivo salvo com sucesso.",
                HttpStatus.OK,
                null);
    }

    @GetMapping("/track/signedCookie")
    public ResponseEntity<Object> getMediaSignedCookie(HttpServletResponse response) 
            throws MalformedURLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        mediaBusiness.getMediaSignedCookie(response);

        return ResponseHandler.generateResponse("Cookie de acesso gerado com sucesso.",
                HttpStatus.OK,
                null);
    }

    public ResponseEntity<Object> fallbackResponse(MultipartFile file, Media media, EpisodeTrack episodeTrack) {
        return ResponseHandler.generateResponse(
                "Serviço está enfrentando alguns problemas. From Media-api Fallback Response",
                HttpStatus.GATEWAY_TIMEOUT,
                null);
    }

    @ExceptionHandler(MediaNotFoundException.class)
    public ResponseEntity<Object> unkownError(MediaNotFoundException e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(PreviewMediaNotFoundException.class)
    public ResponseEntity<Object> unkownError(PreviewMediaNotFoundException e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> unkownError(MaxUploadSizeExceededException e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, null);
    }

}
