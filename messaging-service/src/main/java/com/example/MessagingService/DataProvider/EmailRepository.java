package com.example.MessagingService.DataProvider;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.MessagingService.Models.Email;

public interface EmailRepository extends MongoRepository<Email, Long> {

}