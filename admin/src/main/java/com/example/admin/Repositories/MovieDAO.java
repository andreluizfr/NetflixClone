package com.example.admin.Repositories;

import java.util.List;

import com.example.admin.Models.Movie;

public interface MovieDAO {
	
	public abstract void save(Movie m);
	
	public List<Movie> findAll();
	
}