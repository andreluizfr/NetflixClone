package com.example.MessagingService.Email.Business;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.MessagingService.Schemas.CreateMessageSchema;

@Service
public class EmailBusiness {

    @KafkaListener(topics = "user-created", groupId = "group-1")
    public void sendNewUserEmail(ConsumerRecord<String, CreateMessageSchema> record) {
        CreateMessageSchema createMessageSchema = record.value();
        System.out.println(createMessageSchema.toString());
    }
}
