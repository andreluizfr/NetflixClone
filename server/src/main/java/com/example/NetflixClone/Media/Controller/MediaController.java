package com.example.NetflixClone.Media.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Exceptions.PreviewMediaNotFoundException;
import com.example.NetflixClone.Media.Business.MediaBusiness;
import com.example.NetflixClone.Media.Models.MediaList;
import com.example.NetflixClone.Media.Models.Movie;
import com.example.NetflixClone.Media.Models.PreviewMedia;
import com.example.NetflixClone.Util.ResponseHandler;

@RestController
@RequestMapping("/api")
public class MediaController {

    @Autowired
    MediaBusiness mediaBusiness;

    @GetMapping("/mediaList/getAll")
    public ResponseEntity<Object> getAllMediaLists() {

        try {

            List<MediaList> mediaLists = mediaBusiness.getAllMediaLists();

            return ResponseHandler.generateResponse("Lista buscada com sucesso.", HttpStatus.OK,
                    mediaLists);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse("Falha ao buscar listas.", HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }

    @GetMapping("/movie/getAll")
    public ResponseEntity<Object> getAllMovies() {

        List<Movie> moviesList = mediaBusiness.getAllMovies();

        return ResponseHandler.generateResponse("Filmes buscados com sucesso.", HttpStatus.OK,
                moviesList);
    }

    @GetMapping("/previewMedia/getCurrent")
    public ResponseEntity<Object> getCurrentPreviewMedia() {

        try {

            PreviewMedia previewMedia = mediaBusiness.getCurrentPreviewMedia();

            return ResponseHandler.generateResponse("Preview buscado com sucesso.", HttpStatus.OK,
                    previewMedia);

        } catch (PreviewMediaNotFoundException e) {

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,
                    null);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse("Falha ao buscar Preview", HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }
}
