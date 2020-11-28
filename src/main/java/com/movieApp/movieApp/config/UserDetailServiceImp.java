package com.movieApp.movieApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.movieApp.movieApp.dao.AuserDao;
import com.movieApp.movieApp.entities.Auser;

public class UserDetailServiceImp implements UserDetailsService {

	
	@Autowired
	private AuserDao auserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Auser auser = auserRepo.getEmailByUserName(username);
		
		if(auser == null)
		{
			throw new UsernameNotFoundException("User is not there");
		}
	 
		CustomUserDetail customeUserDetail = new CustomUserDetail(auser);
		
		
		return customeUserDetail;
	}

}
