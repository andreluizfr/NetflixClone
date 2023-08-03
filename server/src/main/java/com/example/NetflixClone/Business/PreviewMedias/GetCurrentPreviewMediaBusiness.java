package com.example.NetflixClone.Business.PreviewMedias;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.CustomExceptions.PreviewMediaNotFoundException;
import com.example.NetflixClone.Models.PreviewMedia;
import com.example.NetflixClone.Repositories.PreviewMediaRepositoryDAO;

@Service
public class GetCurrentPreviewMediaBusiness {
    
    @Autowired
    PreviewMediaRepositoryDAO previewMediaRepository;

    public PreviewMedia execute() throws PreviewMediaNotFoundException {
       
        Optional<PreviewMedia> previewMedia = previewMediaRepository.findTopByOrderByCreatedAtDesc();

        if(previewMedia.isPresent()) return previewMedia.get();

        else throw new PreviewMediaNotFoundException("Nenhuma preview encontrada.");
    }
}
