package com.movieApp.movieApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movieApp.movieApp.entities.Movie;

public interface MovieDoa extends JpaRepository<Movie, Integer> {
	
	@Query("from Movie as m where m.auser.id = :id")
	public List<Movie> getMoviesByUserId(@Param("id") int id);

}
