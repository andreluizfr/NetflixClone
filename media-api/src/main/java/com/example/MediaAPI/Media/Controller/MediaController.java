package com.example.MediaAPI.Media.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MediaAPI.Media.Business.MediaBusiness;
import com.example.MediaAPI.Media.Exceptions.MediaNotFoundException;
import com.example.MediaAPI.Media.Exceptions.PreviewMediaNotFoundException;
import com.example.MediaAPI.Media.Models.Anime;
import com.example.MediaAPI.Media.Models.Media;
import com.example.MediaAPI.Media.Models.MediaList;
import com.example.MediaAPI.Media.Models.Movie;
import com.example.MediaAPI.Media.Models.PreviewMedia;
import com.example.MediaAPI.Media.Models.TvShow;
import com.example.MediaAPI.Util.ResponseHandler;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/api")
public class MediaController {

    @Autowired
    MediaBusiness mediaBusiness;

    @Autowired
    Gson gson;

    public ResponseEntity<Object> fallbackResponse() {
        return ResponseHandler.generateResponse("Serviço está enfrentando alguns problemas.", 
                HttpStatus.INTERNAL_SERVER_ERROR,
                null);
    }

    public ResponseEntity<Object> fallbackResponse(Long id) {
        return ResponseHandler.generateResponse("Serviço está enfrentando alguns problemas.", 
                HttpStatus.INTERNAL_SERVER_ERROR,
                null);
    }

    @HystrixCommand(
        commandKey= "/media",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass  10s with circuit open to the fallbackMethod
    })
    @GetMapping("/media")
    public ResponseEntity<Object> getAllMedias() {
        List<Media> mediasList = mediaBusiness.getAllMedias();

        return ResponseHandler.generateResponse("Lista de mídias buscada com sucesso.",
                HttpStatus.OK,
                mediasList);
    }


    @HystrixCommand(
        commandKey= "/media?id",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass 10s with circuit open to the fallbackMethod
    })
    @GetMapping(value = "/media", params = "id")
    public ResponseEntity<Object> getMedia(@RequestParam(name = "id", required = true) Long id) 
            throws MediaNotFoundException{
        
        Media media = gson.fromJson(mediaBusiness.getMedia(id), Media.class);

        return ResponseHandler.generateResponse("Mídia buscada com sucesso.", 
                HttpStatus.OK,
                media);
    }

    @HystrixCommand(
        commandKey= "/movie",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass 10s with circuit open to the fallbackMethod
    })
    @GetMapping("/movie")
    public ResponseEntity<Object> getAllMovies() {

        List<Movie> moviesList = mediaBusiness.getAllMovies();

        return ResponseHandler.generateResponse("Filmes buscados com sucesso.", 
                HttpStatus.OK,
                moviesList);
    }

    @HystrixCommand(
        commandKey= "/tvShow",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass 10s with circuit open to the fallbackMethod
    })
    @GetMapping("/tvShow")
    public ResponseEntity<Object> getAllTvShows() {

        List<TvShow> tvShowsList = mediaBusiness.getAllTvShows();

        return ResponseHandler.generateResponse("Séries buscadas com sucesso.", 
                HttpStatus.OK,
                tvShowsList);
    }

    @HystrixCommand(
        commandKey= "/anime",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass 10s with circuit open to the fallbackMethod
    })
    @GetMapping("/anime")
    public ResponseEntity<Object> getAllAnimes() {

        List<Anime> animesList = mediaBusiness.getAllAnimes();

        return ResponseHandler.generateResponse("Animes buscados com sucesso.", 
                HttpStatus.OK,
                animesList);
    }

    @HystrixCommand(
        commandKey= "/previewMedia/getCurrent",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass 10s with circuit open to the fallbackMethod
    })
    @GetMapping("/previewMedia/getCurrent")
    public ResponseEntity<Object> getCurrentPreviewMedia() 
            throws PreviewMediaNotFoundException {

        PreviewMedia previewMedia = mediaBusiness.getCurrentPreviewMedia();

        return ResponseHandler.generateResponse("Preview buscado com sucesso.",
                HttpStatus.OK,
                previewMedia);
    }

    @HystrixCommand(
        commandKey= "/mediaList",
        fallbackMethod = "fallbackResponse",
        commandProperties = {
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"), //the method that in 10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //received at least 5 requests
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //and got 60% error rate
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //will pass 10s with circuit open to the fallbackMethod
    })
    @GetMapping("/mediaList")
    public ResponseEntity<Object> getAllMediaLists() {

        List<MediaList> mediaLists = mediaBusiness.getAllMediaLists();

        return ResponseHandler.generateResponse("Lista buscada com sucesso.", 
                HttpStatus.OK,
                mediaLists);
    }

    @ExceptionHandler(MediaNotFoundException.class)
    public ResponseEntity<Object> unkownError(MediaNotFoundException e) {
        System.out.println(e.getMessage());
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(PreviewMediaNotFoundException.class)
    public ResponseEntity<Object> unkownError(PreviewMediaNotFoundException e) {
        System.out.println(e.getMessage());
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
    }
}
