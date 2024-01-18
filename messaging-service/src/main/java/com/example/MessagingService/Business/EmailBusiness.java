package com.example.MessagingService.Business;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.MessagingService.Models.User;
import com.google.gson.Gson;

@Service
public class EmailBusiness {

    @Autowired
    Gson gson;

    @KafkaListener(topics = "user-created", groupId = "group-1")
    public void sendNewUserEmail(ConsumerRecord<String, String> record) {

        User user = gson.fromJson(record.value(), User.class);

        System.out.println(user);
    }
}
