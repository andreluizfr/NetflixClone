package com.example.admin.Repositories;

import com.example.admin.Models.Movie;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

import com.example.admin.JPAUtil;

public class JPAMovieDAO implements MovieDAO {
	
	public void save(Movie m) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(m);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public List<Movie> findAll() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Movie> movies = em.createQuery("select m from Movie m").getResultList();
		
		em.close();

		return movies;
	}

	public Movie findById(UUID id) {
		//EntityManager em = JPAUtil.getEntityManager();
		
		
		return null;
	}

	public Movie update(Movie m) {
		// TODO Auto-generated method stub
		return null;
	}

	public Movie delete(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}