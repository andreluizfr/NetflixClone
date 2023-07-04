package com.example.admin.Business;

import com.example.admin.JPAUtil;
import com.example.admin.Models.enums.Genre;
import com.example.admin.Repositories.JPAMovieDAO;
import com.example.admin.Repositories.MovieDAO;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "addMovie")
@ViewScoped
public class AddMovie implements Serializable{

   private static final long serialVersionUID = 1L;

   MovieDAO jpaMovieDAO = new JPAMovieDAO();
   
   List<Genre> genresList = new ArrayList<>(Arrays.asList(Genre.values()));
   Genre genreItemState;
   
   String title;
   boolean isAnimated;
   
   List<Genre> genres;
   String director;
   int releaseYear;
   boolean isMovieSeries;
   int sequenceNumber;
   List<String> actorsActresses = new ArrayList<>();
   
   public AddMovie() {
	   this.genres = new ArrayList<>(Arrays.asList(Genre.ACTION, Genre.COMEDY));
   }


   public void setTitle(ValueChangeEvent event) {
	   this.title = (String) event.getNewValue();
   }

   public void setIsAnimated(ValueChangeEvent event) {
	   boolean isAnimated = (boolean) event.getNewValue();
       this.isAnimated = isAnimated;
   }
   
   public List<Genre> getGenresList() {
	   return this.genresList;
   }
   
   public void setGenresList(List<Genre> genresList) {
		this.genresList = genresList;
	}

   public void addGenre() {
	   Genre genre = Genre.FANTASY;
	   this.genres.add(genreItemState);
	   this.genres.add(genre);
	   System.out.println(this.genres);
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

   public void setIsMovieSeriesYear(boolean isMovieSeries) {
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
	
	public boolean isAnimated() {
		return isAnimated;
	}
	
	public void setAnimated(boolean isAnimated) {
		this.isAnimated = isAnimated;
	}
	
	public List<Genre> getGenres() {
		return this.genres;
	}
	
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
	public boolean isMovieSeries() {
		return isMovieSeries;
	}
	
	public void setMovieSeries(boolean isMovieSeries) {
		this.isMovieSeries = isMovieSeries;
	}
	
	public List<String> getActorsActresses() {
		return actorsActresses;
	}
	
	public void setActorsActresses(List<String> actorsActresses) {
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
   
   
}