package com.example.admin.Repositories;

import java.util.List;

import com.example.admin.Models.Anime;

public interface AnimeDAO {
	
	public void save(Anime t);
	
	public List<Anime> getAll();
	
	public Anime findById(Long id);
	
	public void update(Anime t);
	
	public void remove(Long id);
	
}