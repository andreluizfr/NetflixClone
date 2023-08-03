package com.example.NetflixClone.Controllers.PreviewMedias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.NetflixClone.Business.PreviewMedias.GetCurrentPreviewMediaBusiness;
import com.example.NetflixClone.Controllers.ResponseErrorHandler;
import com.example.NetflixClone.CustomExceptions.PreviewMediaNotFoundException;
import com.example.NetflixClone.Models.PreviewMedia;

@RestController
@RequestMapping("/api/previewMedia")
public class GetCurrentPreviewMediaController {

    @Autowired
    GetCurrentPreviewMediaBusiness getCurrentPreviewMediaBusiness;

    @GetMapping("/getCurrent")
    public ResponseEntity<Object> getAllMediaLists() {

        try {

            PreviewMedia previewMedia = getCurrentPreviewMediaBusiness.execute();

            return ResponseErrorHandler.generateResponse("Preview buscado com sucesso.", HttpStatus.OK,
                    previewMedia);

        } catch (PreviewMediaNotFoundException e) {

            return ResponseErrorHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,
                    null);

        } catch (RuntimeException e) {

            e.printStackTrace();

            return ResponseErrorHandler.generateResponse("Falha ao buscar Preview", HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }

    }
}
