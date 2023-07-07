package com.example.admin.Repositories;

import java.util.List;

import com.example.admin.Models.Movie;

public interface MovieDAO {
	
	public void save(Movie m);
	
	public List<Movie> getAll();
	
	public Movie findById(Long id);
	
	public void update(Movie m);
	
	public void remove(Long id);
	
}