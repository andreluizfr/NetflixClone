package com.example.NetflixClone.Controllers.MediaLists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.Repositories.MediaListRepositoryDAO;

import com.example.NetflixClone.Models.MediaList;

@RestController
@RequestMapping("/api/mediaList")
public class GetAllMediaListsController {

    @Autowired
    MediaListRepositoryDAO mediaListRepository;
    
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllMediaLists() {

            List<MediaList> mediaList = mediaListRepository.findAll();

            return ResponseErrorHandler.generateResponse("Lista buscado com sucesso.", HttpStatus.OK,
                    mediaList);

    }
}
