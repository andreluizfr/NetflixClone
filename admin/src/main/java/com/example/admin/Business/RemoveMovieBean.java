package com.example.admin.Business;

import com.example.admin.Models.Movie;
import com.example.admin.Repositories.MovieDAOImpl;
import com.example.admin.Repositories.MovieDAO;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "removeMovieBean")
@ViewScoped
public class RemoveMovieBean implements Serializable{

   private static final long serialVersionUID = 1L;

   MovieDAO movieDAOImpl = new MovieDAOImpl();
   
   List<Movie> movieList;
   
   Long movieToRemoveIdState;
   
   String messageState;
   
   public RemoveMovieBean() {
	   this.movieList = movieDAOImpl.getAll();
	   System.out.println(this.movieList);
   }
     
   public void handleRemove(Long id) {
	 
	   try {
		   System.out.println("###########"+id+"############");
		   this.movieDAOImpl.remove(id);
		   
		   messageState = "Filme removido com sucesso.";
		   
		   this.movieList = movieDAOImpl.getAll();
		   
	   } catch (Exception e) {
		   
		   messageState = e.getMessage();
	   };
   }
   
   public List<Movie> getMovieList() {
	   return this.movieList;
   }
   public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
   }
   
   public Long getMovieToRemoveIdState() {
	   return this.movieToRemoveIdState;
   }
   public void setMovieToRemoveIdState(Long movieToRemoveIdState) {
		this.movieToRemoveIdState = movieToRemoveIdState;
   }
   public String getMessageState() {
	   return messageState;
   }
   public void setMessageState(String messageState) {
	   this.messageState = messageState;
   }


}