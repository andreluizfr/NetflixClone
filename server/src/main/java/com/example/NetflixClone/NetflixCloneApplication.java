package com.example.NetflixClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class NetflixCloneApplication{

	public static void main(String[] args) {
		SpringApplication.run(NetflixCloneApplication.class, args);
	}
}
