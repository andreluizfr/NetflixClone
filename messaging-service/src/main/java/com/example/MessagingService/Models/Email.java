package com.example.MessagingService.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Email {
    @Id
    private Long id;
}
