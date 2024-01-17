package com.example.UserAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class UserAPIApplication{

	public static void main(String[] args) {
		SpringApplication.run(UserAPIApplication.class, args);
	}
}
