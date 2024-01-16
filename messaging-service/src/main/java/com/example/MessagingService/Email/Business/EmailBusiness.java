package com.example.MessagingService.Email.Business;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailBusiness {

    @KafkaListener(topics = "user-created", groupId = "group-1")
    public void sendNewUserEmail(ConsumerRecord<String, String> record) {
        String userEmail = record.value();
        System.out.println(userEmail);
    }
}
