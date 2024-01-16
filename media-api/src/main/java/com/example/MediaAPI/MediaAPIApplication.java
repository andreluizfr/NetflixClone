package com.example.MediaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
public class MediaAPIApplication{

	public static void main(String[] args) {
		SpringApplication.run(MediaAPIApplication.class, args);
	}
}
