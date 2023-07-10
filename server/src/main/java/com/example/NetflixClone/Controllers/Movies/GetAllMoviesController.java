package com.example.NetflixClone.Controllers.Movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.Repositories.MovieRepositoryDAO;
import com.example.NetflixClone.Models.Movie;

@RestController
@RequestMapping("/api/movie")
public class GetAllMoviesController {

    @Autowired
    MovieRepositoryDAO movieRepository;
    
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllMoviesLists() {

            List<Movie> mediaList = movieRepository.findAll();

            return ResponseErrorHandler.generateResponse("Filmes buscados com sucesso.", HttpStatus.OK,
                    mediaList);

    }
}
