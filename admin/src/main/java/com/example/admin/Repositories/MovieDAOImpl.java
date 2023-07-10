package com.example.admin.Repositories;

import com.example.admin.Models.Movie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import com.example.admin.JPAUtil;

public class MovieDAOImpl implements MovieDAO {
	
	public void save(Movie m) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(m);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public List<Movie> getAll() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Movie> movies = em.createQuery("select m from Movie m").getResultList();
		
		em.close();

		return movies;
	}

	public Movie findById(Long id) {

		EntityManager em = JPAUtil.getEntityManager();
		
		Movie movie = em.find(Movie.class, id);

        if (movie == null) 
            throw new EntityNotFoundException("Can't find movie for id "
                    + id);

        return movie;
	}

	public void update(Movie updatedMovie) {
		
		EntityManager em = JPAUtil.getEntityManager();
		Movie movie = this.findById(updatedMovie.getId());
		
		try {
			em.getTransaction().begin();
			
			movie.setTitle(updatedMovie.getTitle());
			movie.setIsAnimation(updatedMovie.getIsAnimation());
			movie.setGenres(updatedMovie.getGenres());
			movie.setDirector(updatedMovie.getDirector());
			movie.setReleaseYear(updatedMovie.getReleaseYear());
			movie.setDescriptions(updatedMovie.getDescriptions());
			movie.setAgeRating(updatedMovie.getAgeRating());
			movie.setThumbnailUrl(updatedMovie.getThumbnailUrl());
			movie.setThumbnailBlurHash(updatedMovie.getThumbnailBlurHash());
			movie.setIsMovieSeries(updatedMovie.getIsMovieSeries());
			movie.setSequenceNumber(updatedMovie.getSequenceNumber());
			movie.setActorsActresses(updatedMovie.getActorsActresses());
	
			em.getTransaction().commit();
        
		} catch(Exception e) {
            em.getTransaction().rollback();
        }
        
        em.close();
	}

	public void remove(Long id) {

		EntityManager em = JPAUtil.getEntityManager();

		Movie movieReference = em.getReference(Movie.class, id); //cria o objeto com apenas id nele
		
		em.getTransaction().begin();
		
		em.remove(movieReference);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
}