package com.example.admin.Business;

import com.example.admin.Models.Movie;
import com.example.admin.Models.enums.Genre;
import com.example.admin.Repositories.JPAMovieDAO;
import com.example.admin.Repositories.MovieDAO;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "addMovieBean")
@ViewScoped
public class AddMovieBean implements Serializable{

   private static final long serialVersionUID = 1L;

   MovieDAO jpaMovieDAO = new JPAMovieDAO();
   
   List<Genre> genresList = new ArrayList<>(Arrays.asList(Genre.values()));

   Genre genreItemState;
   String actorsActressesState;
   
   String title;
   boolean isAnimation;
   Set<Genre> genres = new HashSet<>();
   String director;
   int releaseYear;
   String description;
   int ageRating = 0;
   String thumbnailUrl;
   String thumbnailBlurHash;
   
   boolean isMovieSeries = false; //próprio da entidade movie
   int sequenceNumber = 1; //próprio da entidade movie
   Set<String> actorsActresses = new HashSet<>(); //próprio da entidade movie
   
   String messageState;
   
   
   public void addGenre() {
	   this.genres.add(this.genreItemState);
   }
   
   public void updateActorsActressesList() {
	   this.actorsActresses = new HashSet<>(Arrays.asList(this.actorsActressesState.split(",")));
   }
   
   public void handleSubmit() {
	   
	   Movie m = new Movie(title, isAnimation, new ArrayList<>(genres), director, releaseYear, description,
			   ageRating, thumbnailUrl, thumbnailBlurHash, isMovieSeries, sequenceNumber, new ArrayList<>(actorsActresses));
	   
	   try {
		   this.jpaMovieDAO.save(m);
		   messageState = "Filme adicionado com sucesso.";
	   } catch (Exception e) {
		   messageState = e.getMessage();
	   };
   }
   
   
   public List<Genre> getGenresList() {
	   return this.genresList;
   }
   public void setGenresList(List<Genre> genresList) {
		this.genresList = genresList;
	}
   public void setDirector(String director) {
      this.director = director;
   }
   public int getReleaseYear() {
      return this.releaseYear;
   }
   public void setReleaseYear(int releaseYear) {
      this.releaseYear = releaseYear;
   }
   public void setIsMovieSeries(boolean isMovieSeries) {
      this.isMovieSeries = isMovieSeries;
   }
   public void setSequenceNumber(int sequenceNumber) {
      this.sequenceNumber = sequenceNumber;
   }
   public void addActorsActresses(String actorOrActress) {
	   this.actorsActresses.add(actorOrActress);
   }
   public String getTitle() {
		return title;
	}
   public void setTitle(String title) {
	   this.title = title;
   }
   public boolean getIsAnimation() {
	   return this.isAnimation;
   }
   public void setIsAnimation(boolean isAnimation) {
	   this.isAnimation = isAnimation;
   }
   public Set<Genre> getGenres() {
	   return this.genres;
   }
   public void setGenres(Set<Genre> genres) {
	   this.genres = genres;
   }
   public boolean getIsMovieSeries() {
	   return isMovieSeries;
   }
   public void setMovieSeries(boolean isMovieSeries) {
	   this.isMovieSeries = isMovieSeries;
   }
   public Set<String> getActorsActresses() {
	   return actorsActresses;
   }
   public void setActorsActresses(Set<String> actorsActresses) {
	   this.actorsActresses = actorsActresses;
   }	
   public String getDirector() {
	   return director;
   }
   public int getSequenceNumber() {
	   return sequenceNumber;
   }
   public void setGenreItemState (Genre genreItemState) {
	   this.genreItemState = genreItemState;
   }
   public Genre getGenreItemState () {
	   return this.genreItemState;
   }
   public String getActorsActressesState () {
	   return this.actorsActressesState;
   }
   public void setActorsActressesState (String actorsActressesState) {
	   this.actorsActressesState = actorsActressesState;
   }
   public String getDescription() {
	   return description;
   }
   public void setDescription(String description) {
	   this.description = description;
   }
   public int getAgeRating() {
	   return ageRating;
   }
   public void setAgeRating(int ageRating) {
	   this.ageRating = ageRating;
   }
   public String getThumbnailUrl() {
	   return thumbnailUrl;
   }
   public void setThumbnailUrl(String thumbnailUrl) {
	   this.thumbnailUrl = thumbnailUrl;
   }
   public String getThumbnailBlurHash() {
	   return thumbnailBlurHash;
   }
   public void setThumbnailBlurHash(String thumbnailBlurHash) {
	   this.thumbnailBlurHash = thumbnailBlurHash;
   }
   public String getMessageState() {
	   return messageState;
   }
   public void setMessageState(String messageState) {
	   this.messageState = messageState;
   }


}