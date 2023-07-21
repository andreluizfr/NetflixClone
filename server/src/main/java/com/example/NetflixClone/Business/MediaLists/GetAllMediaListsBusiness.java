package com.example.NetflixClone.Business.MediaLists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Models.MediaList;
import com.example.NetflixClone.Repositories.MediaListRepositoryDAO;

@Service
public class GetAllMediaListsBusiness {

    @Autowired
    MediaListRepositoryDAO MediaListRepository;

    public List<MediaList> execute() {

        List<MediaList> mediaLists = MediaListRepository.findAll();

        return mediaLists;
    }
}
