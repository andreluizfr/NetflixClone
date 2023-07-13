package com.example.admin.Business;

import com.example.admin.Models.Movie;
import com.example.admin.Models.enums.Genre;
import com.example.admin.Repositories.MovieDAOImpl;
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
public class AddMovieBean implements Serializable {

   private static final long serialVersionUID = 1L;

   MovieDAO movieDAOImpl = new MovieDAOImpl();
   Movie movieToAdd = new Movie();

   List<Genre> genresList = new ArrayList<>(Arrays.asList(Genre.values()));

   Genre genreItemState;
   String actorsActressesState;
   Set<String> actorsActressesSet = new HashSet<>();

   String messageState;

   public AddMovieBean() {
      this.movieToAdd.setGenres(new ArrayList<>());
      this.movieToAdd.setAgeRating(0);
      this.movieToAdd.setIsMovieSeries(false);
      this.movieToAdd.setSequenceNumber(1);
      this.movieToAdd.setActorsActresses(new ArrayList<>());
   }

   public void addGenre() {
      Set<Genre> oldGenres = new HashSet<>(this.movieToAdd.getGenres());
      oldGenres.add(this.genreItemState);
      this.movieToAdd.setGenres(new ArrayList<>(oldGenres));
   }

   public void updateActorsActressesList() {
      this.actorsActressesSet = new HashSet<>(Arrays.asList(this.actorsActressesState.split(",")));
      this.movieToAdd.setActorsActresses(new ArrayList<>(this.actorsActressesSet));
   }

   public void handleSubmit() {

      try {
         this.movieDAOImpl.save(this.movieToAdd);
         messageState = "Filme adicionado com sucesso.";
      } catch (Exception e) {
         messageState = e.getMessage();
      }
      ;
   }

   public Movie getMovieToAdd() {
      return this.movieToAdd;
   }

   public void setMovieToAdd(Movie movieToAdd) {
      this.movieToAdd = movieToAdd;
   }

   public List<Genre> getGenresList() {
      return this.genresList;
   }

   public void setGenresList(List<Genre> genresList) {
      this.genresList = genresList;
   }

   public void setGenreItemState(Genre genreItemState) {
      this.genreItemState = genreItemState;
   }

   public Genre getGenreItemState() {
      return this.genreItemState;
   }

   public String getActorsActressesState() {
      return this.actorsActressesState;
   }

   public void setActorsActressesState(String actorsActressesState) {
      this.actorsActressesState = actorsActressesState;
   }

   public String getMessageState() {
      return messageState;
   }

   public void setMessageState(String messageState) {
      this.messageState = messageState;
   }

}