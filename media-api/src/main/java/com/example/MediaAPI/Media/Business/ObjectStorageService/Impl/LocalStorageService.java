package com.example.MediaAPI.Media.Business.ObjectStorageService.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.MediaAPI.Media.Business.ObjectStorageService.IObjectStorageService;

@Service
@Qualifier("LocalStorageService")
public class LocalStorageService implements IObjectStorageService {
    
    private static final Logger logger = LogManager.getLogger(LocalStorageService.class);

    private final String bucketName = "netflixClone";

    @Override
    public void listObjects() {}

    @Override
    public void listBuckets() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listBuckets'");
    }

    @Override
    public void deleteBucket() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBucket'");
    }

    @Override
    public void deleteObjectsInBucket() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteObjectsInBucket'");
    }

    @Override
    public void getObject() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getObject'");
    }
}
