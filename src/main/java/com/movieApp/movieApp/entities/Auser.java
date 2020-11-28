package com.movieApp.movieApp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Auser")

public class Auser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String password;
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auser", orphanRemoval = true)
	List<Movie> movie = new ArrayList<>();

	
	
	
	public Auser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Movie> getMovie() {
		return movie;
	}
	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}
	@Override
	public String toString() {
		return "Auser [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", movie=" + movie + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
