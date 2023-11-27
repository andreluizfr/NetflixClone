package com.example.MediaService.Media.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MediaService.Media.Business.MediaBusiness;
import com.example.MediaService.Media.Exceptions.MediaNotFoundException;
import com.example.MediaService.Media.Exceptions.PreviewMediaNotFoundException;
import com.example.MediaService.Media.Models.Anime;
import com.example.MediaService.Media.Models.Media;
import com.example.MediaService.Media.Models.MediaList;
import com.example.MediaService.Media.Models.Movie;
import com.example.MediaService.Media.Models.PreviewMedia;
import com.example.MediaService.Media.Models.TvShow;
import com.example.MediaService.Util.ResponseHandler;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    MediaBusiness mediaBusiness;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllMedias() {

        List<Media> mediasList = mediaBusiness.getAllMedias();

        return ResponseHandler.generateResponse("Lista de mídias buscada com sucesso.", 
                HttpStatus.OK,
                mediasList);
    }

    @GetMapping(value = "/get", params = "id")
    public ResponseEntity<Object> getMedia(@RequestParam(name = "id", required = false) Long id) 
            throws MediaNotFoundException{

        Media media = mediaBusiness.getMedia(id);

        return ResponseHandler.generateResponse("Mídia buscada com sucesso.", 
                HttpStatus.OK,
                media);
    }

    @GetMapping("/movie/getAll")
    public ResponseEntity<Object> getAllMovies() {

        List<Movie> moviesList = mediaBusiness.getAllMovies();

        return ResponseHandler.generateResponse("Filmes buscados com sucesso.", 
                HttpStatus.OK,
                moviesList);
    }

    @GetMapping("/tvShow/getAll")
    public ResponseEntity<Object> getAllTvShows() {

        List<TvShow> tvShowsList = mediaBusiness.getAllTvShows();

        return ResponseHandler.generateResponse("Séries buscadas com sucesso.", 
                HttpStatus.OK,
                tvShowsList);
    }

    @GetMapping("/anime/getAll")
    public ResponseEntity<Object> getAllAnimes() {

        List<Anime> animesList = mediaBusiness.getAllAnimes();

        return ResponseHandler.generateResponse("Animes buscados com sucesso.", 
                HttpStatus.OK,
                animesList);
    }

    @GetMapping("/previewMedia/getCurrent")
    public ResponseEntity<Object> getCurrentPreviewMedia() 
            throws PreviewMediaNotFoundException {

        PreviewMedia previewMedia = mediaBusiness.getCurrentPreviewMedia();

        return ResponseHandler.generateResponse("Preview buscado com sucesso.",
                HttpStatus.OK,
                previewMedia);
    }

    @GetMapping("/mediaList/getAll")
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
