package com.movieApp.movieApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movieApp.movieApp.entities.Auser;

public interface AuserDao extends JpaRepository<Auser, Integer> {
	
	@Query("select u from Auser u where u.email = :email")
	public Auser getEmailByUserName(@Param("email") String email);

}
