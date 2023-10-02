package com.example.NetflixClone.Media.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Exceptions.MediaNotFoundException;
import com.example.NetflixClone.Exceptions.PreviewMediaNotFoundException;
import com.example.NetflixClone.Media.Business.MediaBusiness;
import com.example.NetflixClone.Media.Models.Anime;
import com.example.NetflixClone.Media.Models.Media;
import com.example.NetflixClone.Media.Models.MediaList;
import com.example.NetflixClone.Media.Models.Movie;
import com.example.NetflixClone.Media.Models.PreviewMedia;
import com.example.NetflixClone.Media.Models.TvShow;
import com.example.NetflixClone.Util.ResponseHandler;

@RestController
@RequestMapping("/api")
public class MediaController {

    @Autowired
    MediaBusiness mediaBusiness;

    @GetMapping("/media/getAll")
    public ResponseEntity<Object> getAllMedias() {

        try {

            List<Media> mediasList = mediaBusiness.getAllMedias();

            return ResponseHandler.generateResponse("Lista de mídias buscada com sucesso.", HttpStatus.OK,
                    mediasList);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse("Falha ao buscar lista de mídias.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }

    @GetMapping(value = "/media/get", params = "id")
    public ResponseEntity<Object> getMedia(@RequestParam(name = "id", required = false) Long id) {

        try {
            Media media = mediaBusiness.getMedia(id);

            return ResponseHandler.generateResponse("Mídia buscada com sucesso.", HttpStatus.OK,
                    media);
        } catch (MediaNotFoundException e) {

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,
                    null);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse("Falha ao buscar mídia.", HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }

    @GetMapping("/media/movie/getAll")
    public ResponseEntity<Object> getAllMovies() {

        try {
            List<Movie> moviesList = mediaBusiness.getAllMovies();

            return ResponseHandler.generateResponse("Filmes buscados com sucesso.", HttpStatus.OK,
                    moviesList);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseHandler.generateResponse("Falha ao buscar filmes.", HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }

    @GetMapping("/media/tvShow/getAll")
    public ResponseEntity<Object> getAllTvShows() {

        List<TvShow> tvShowsList = mediaBusiness.getAllTvShows();

        return ResponseHandler.generateResponse("Séries buscadas com sucesso.", HttpStatus.OK,
                tvShowsList);
    }

    @GetMapping("/media/anime/getAll")
    public ResponseEntity<Object> getAllAnimes() {

        List<Anime> animesList = mediaBusiness.getAllAnimes();

        return ResponseHandler.generateResponse("Animes buscados com sucesso.", HttpStatus.OK,
                animesList);
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
}
