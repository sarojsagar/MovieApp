package com.movieApp.movieApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Movie")

public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mid;
	private String movieName;
	private String rating;
	private String yearReleased;
	private String about;
	
	@ManyToOne
	private Auser auser;
	
	
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getYearReleased() {
		return yearReleased;
	}
	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Auser getAuser() {
		return auser;
	}
	public void setAuser(Auser auser) {
		this.auser = auser;
	}
	@Override
	public String toString() {
		return "Movie [mid=" + mid + ", movieName=" + movieName + ", rating=" + rating + ", yearReleased="
				+ yearReleased + ", about=" + about + ", auser=" + auser + "]";
	}
	
	
	
	

}
