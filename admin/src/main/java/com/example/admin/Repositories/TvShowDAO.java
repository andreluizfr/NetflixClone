package com.example.admin.Repositories;

import java.util.List;

import com.example.admin.Models.TvShow;

public interface TvShowDAO {
	
	public void save(TvShow t);
	
	public List<TvShow> getAll();
	
	public TvShow findById(Long id);
	
	public void update(TvShow t);
	
	public void remove(Long id);
	
}