package com.example.NetflixClone.Controllers.MediaLists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.MediaLists.GetAllMediaListsBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;

import com.example.NetflixClone.Models.MediaList;

@RestController
@RequestMapping("/api/mediaList")
public class GetAllMediaListsController {

    @Autowired
    GetAllMediaListsBusiness getAllMediaListsBusiness;
    
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllMediaLists() {

        try {

            List<MediaList> mediaLists = getAllMediaListsBusiness.execute();

            return ResponseErrorHandler.generateResponse("Lista buscada com sucesso.", HttpStatus.OK,
                    mediaLists);

        } catch (RuntimeException e) {

            e.printStackTrace();
            
            return ResponseErrorHandler.generateResponse("Falha ao buscar listas.", HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }   
    
    }
}
