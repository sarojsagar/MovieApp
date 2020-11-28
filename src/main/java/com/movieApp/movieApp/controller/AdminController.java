package com.movieApp.movieApp.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movieApp.movieApp.dao.AuserDao;
import com.movieApp.movieApp.dao.MovieDoa;
import com.movieApp.movieApp.entities.Auser;
import com.movieApp.movieApp.entities.Movie;

@Controller
@RequestMapping("/movieadmin")
public class AdminController {
	
	@Autowired
	private AuserDao auserRepo;
	
	@Autowired
	private MovieDoa movieRepo;

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String adminHome()
	{
	
		return "admin/index";
	}
	
	@RequestMapping(value="/add-movie", method=RequestMethod.GET)
	public String addMovies(Model model)
	{
	
		model.addAttribute("movie", new Movie());
		
		return "admin/add-movies";
	}
	
	@RequestMapping(value="/save-movie", method=RequestMethod.POST)
	public String saveMovies(@ModelAttribute("movie") Movie movie, Model model, Principal principal, HttpSession session)
	{
	
		try {
				String userName = principal.getName();
				
				Auser auser = auserRepo.getEmailByUserName(userName);
				
				movie.setAuser(auser);
				auser.getMovie().add(movie);
				auserRepo.save(auser);
				session.setAttribute("message", new com.movieApp.movieApp.helper.Message("Your movie is added and add new movie", "success"));

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new com.movieApp.movieApp.helper.Message("Something went wrong", "danger"));
		}
		
		return "redirect:/movieadmin/add-movie";
	}
	
	
	@RequestMapping(value="/movie-list", method=RequestMethod.GET)
	public String movielist(Model model, Principal principal)
	{
		String userName = principal.getName();
		Auser auser = this.auserRepo.getEmailByUserName(userName);
		
		List<Movie> movieList = movieRepo.getMoviesByUserId(auser.getId());
		
		model.addAttribute("movies", movieList);
	
		return "admin/movies-list";
	}
	
	@RequestMapping(value="/delete/{mid}", method=RequestMethod.GET)
	public String deleteMovie(@PathVariable("mid") Integer mid, Model model, Principal principal, HttpSession session)
	{
		Optional<Movie> movieOptional = this.movieRepo.findById(mid);
		
		Movie movie = movieOptional.get();
	
		
		Auser auser = this.auserRepo.getEmailByUserName(principal.getName());
		auser.getMovie().remove(movie);
		this.auserRepo.save(auser);
		
		session.setAttribute("message", new com.movieApp.movieApp.helper.Message("Movie has been deleted successfully", "success"));
	
		return "redirect:/movieadmin/movie-list";
	}
	
	
}
