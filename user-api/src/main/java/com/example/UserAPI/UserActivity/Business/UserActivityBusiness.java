package com.example.UserAPI.UserActivity.Business;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.UserAPI.UserActivity.DataProvider.IUserActivityDataProvider;
import com.example.UserAPI.UserActivity.Models.UserActivity;
import com.example.UserAPI.UserActivity.Models.UserActivityDTO;
import com.google.gson.Gson;

@Service
public class UserActivityBusiness {

    @Autowired
    IUserActivityDataProvider userActivityDataProvider;

    @Autowired
    Gson gson;

    @Transactional
    @KafkaListener(topics = "save-user-activity", groupId = "group-1")
    public void saveUserActivity(ConsumerRecord<String, String> record) {

        UserActivityDTO userActivityDTO = gson.fromJson(record.value(), UserActivityDTO.class);

        UserActivity userActivity = new UserActivity();
        userActivity.setUser(userActivityDTO.getUser());
        userActivity.setIp(userActivityDTO.getIp());
        userActivity.setProfile(userActivityDTO.getProfile());
        userActivity.setPermissionName(userActivityDTO.getPermissionName());
        userActivity.setCreatedAt(LocalDateTime.now());
        userActivity.setUpdatedAt(LocalDateTime.now());

        userActivityDataProvider.saveOrUpdate(userActivity);
    }
}
