package com.example.NetflixClone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.NetflixClone.BD.InitialQueries;

@SpringBootApplication
public class NetflixCloneApplication{

	public static void main(String[] args) {
		SpringApplication.run(NetflixCloneApplication.class, args);
	}

	@Autowired
	private InitialQueries initialQueries;

	@PostConstruct
	public void init() {
		initialQueries.populateUsers();
		initialQueries.populateMoviesAndLists();
	}
}
