package com.example.admin.Repositories;

import java.util.List;
import java.util.UUID;

import com.example.admin.Models.Movie;

public interface MovieDAO {
	
	public void save(Movie m);
	
	public List<Movie> findAll();
	
	public Movie findById(UUID id);
	
	public Movie update(Movie m);
	
	public Movie delete(UUID id);
	
}