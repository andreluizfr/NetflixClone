package com.example.admin.Repositories;

import com.example.admin.Models.TvShow;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import com.example.admin.JPAUtil;

public class TvShowDAOImpl implements TvShowDAO {
	
	public void save(TvShow m) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(m);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public List<TvShow> getAll() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		@SuppressWarnings("unchecked")
		List<TvShow> tvShows = em.createQuery("select t from TvShow t").getResultList();
		
		em.close();

		return tvShows;
	}

	public TvShow findById(Long id) {

		EntityManager em = JPAUtil.getEntityManager();
		
		TvShow tvShow = em.find(TvShow.class, id);

        if (tvShow == null) 
            throw new EntityNotFoundException("Can't find tv show for id "
                    + id);

        return tvShow;
	}

	public void update(TvShow updatedTvShow) {
		
		EntityManager em = JPAUtil.getEntityManager();
		TvShow tvShow = this.findById(updatedTvShow.getId());
		
		try {
			em.getTransaction().begin();
			
			tvShow.setTitle(updatedTvShow.getTitle());
			tvShow.setIsAnimation(updatedTvShow.getIsAnimation());
			tvShow.setGenres(updatedTvShow.getGenres());
			tvShow.setDirector(updatedTvShow.getDirector());
			tvShow.setReleaseYear(updatedTvShow.getReleaseYear());
			tvShow.setDescription(updatedTvShow.getDescription());
			tvShow.setAgeRating(updatedTvShow.getAgeRating());
			tvShow.setThumbnailUrl(updatedTvShow.getThumbnailUrl());
			tvShow.setThumbnailBlurHash(updatedTvShow.getThumbnailBlurHash());
			
			tvShow.setNumberOfSeasons(updatedTvShow.getNumberOfSeasons());
			tvShow.setSeasonNumber(updatedTvShow.getSeasonNumber());
			tvShow.setActorsActresses(updatedTvShow.getActorsActresses());
	
			em.getTransaction().commit();
        
		} catch(Exception e) {
            em.getTransaction().rollback();
        }
        
        em.close();
	}

	public void remove(Long id) {

		EntityManager em = JPAUtil.getEntityManager();

		TvShow tvShowReference = em.getReference(TvShow.class, id); //cria o objeto com apenas id nele
		
		em.getTransaction().begin();
		
		em.remove(tvShowReference);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
}