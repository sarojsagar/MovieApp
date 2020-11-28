package com.movieApp.movieApp.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.movieApp.movieApp.entities.Auser;

public class CustomUserDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Auser auser;

	
	

	public CustomUserDetail(Auser auser) {
		super();
		this.auser = auser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantAuthority = new SimpleGrantedAuthority(auser.getRole());
		
		return Arrays.asList(simpleGrantAuthority);
	}

	@Override
	public String getPassword() {
		
		return auser.getPassword();
	}

	@Override
	public String getUsername() {
		return auser.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}