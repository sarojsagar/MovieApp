package com.movieApp.movieApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movieApp.movieApp.dao.AuserDao;
import com.movieApp.movieApp.dao.MovieDoa;
import com.movieApp.movieApp.entities.Auser;
import com.movieApp.movieApp.entities.Movie;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuserDao auserRepo;
	
	@Autowired
	private MovieDoa movieRepo;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home()
	{
		return "index";
				
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String movieList(Model model)
	{
		List<Movie> movies = this.movieRepo.findAll();
		model.addAttribute("allMovies", movies);
		return "allmovies";
				
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String RegisterUser(Model model)
	{
		
		model.addAttribute("auser", new Auser());
		return "register";
				
	}
	
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	private String saveUser(@ModelAttribute("auser") Auser auser, Model model, HttpSession session,
			BindingResult results, @RequestParam(value="agreement", defaultValue = "false") boolean agreement)
	{
		
		try {
				if(!agreement)
				{
					System.out.println("Accept our terms and conditions");
				}
				if(results.hasErrors())
				{
					System.out.println("erroe" + results.toString());
					model.addAttribute("auser", auser);
					return "register";
				}
				
					
				auser.setRole("ROLE_ADMIN");
				auser.setPassword(passwordEncoder.encode(auser.getPassword()));
				
				
				Auser result = auserRepo.save(auser);
				System.out.println(results);
				model.addAttribute("auser", new Auser());
				session.setAttribute("message", new com.movieApp.movieApp.helper.Message("Registered Successfylly", "alert-success"));
				
				return "register";
				
			
		} catch (Exception e) {
			session.setAttribute("message", new com.movieApp.movieApp.helper.Message("something went wrong" + e.getMessage(), "alert-danger"));
			return "register";
			
		}
		
		
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String UserLogin()
	{
		return "login";
				
	}
	
	

}
