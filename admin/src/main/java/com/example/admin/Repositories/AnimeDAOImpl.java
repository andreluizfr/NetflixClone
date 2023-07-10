package com.example.admin.Repositories;

import com.example.admin.Models.Anime;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import com.example.admin.JPAUtil;

public class AnimeDAOImpl implements AnimeDAO {
	
	public void save(Anime m) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(m);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public List<Anime> getAll() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Anime> animes = em.createQuery("select a from Anime a").getResultList();
		
		em.close();

		return animes;
	}

	public Anime findById(Long id) {

		EntityManager em = JPAUtil.getEntityManager();
		
		Anime anime = em.find(Anime.class, id);

        if (anime == null) 
            throw new EntityNotFoundException("Can't find anime for id "
                    + id);

        return anime;
	}

	public void update(Anime updatedAnime) {
		
		EntityManager em = JPAUtil.getEntityManager();
		Anime anime = this.findById(updatedAnime.getId());
		
		try {
			em.getTransaction().begin();
			
			anime.setTitle(updatedAnime.getTitle());
			anime.setIsAnimation(updatedAnime.getIsAnimation());
			anime.setGenres(updatedAnime.getGenres());
			anime.setDirector(updatedAnime.getDirector());
			anime.setReleaseYear(updatedAnime.getReleaseYear());
			anime.setDescriptions(updatedAnime.getDescriptions());
			anime.setAgeRating(updatedAnime.getAgeRating());
			anime.setThumbnailUrl(updatedAnime.getThumbnailUrl());
			anime.setThumbnailBlurHash(updatedAnime.getThumbnailBlurHash());
			
			anime.setNumberOfSeasons(updatedAnime.getNumberOfSeasons());
			anime.setSeasonNumber(updatedAnime.getSeasonNumber());
			anime.setStudio(updatedAnime.getStudio());
	
			em.getTransaction().commit();
        
		} catch(Exception e) {
            em.getTransaction().rollback();
        }
        
        em.close();
	}

	public void remove(Long id) {

		EntityManager em = JPAUtil.getEntityManager();

		Anime animeReference = em.getReference(Anime.class, id); //cria o objeto com apenas id nele
		
		em.getTransaction().begin();
		
		em.remove(animeReference);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
}