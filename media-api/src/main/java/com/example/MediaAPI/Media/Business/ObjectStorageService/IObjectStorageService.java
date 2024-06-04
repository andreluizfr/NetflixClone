package com.example.MediaAPI.Media.Business.ObjectStorageService;

public interface IObjectStorageService {

    public void listBuckets();

    public void listObjects();

    public void deleteBucket();

    public void deleteObjectsInBucket();

    public void getObject();
}
