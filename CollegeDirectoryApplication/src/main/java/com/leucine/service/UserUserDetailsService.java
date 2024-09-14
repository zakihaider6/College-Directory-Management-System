package com.leucine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leucine.modal.Users;
import com.leucine.repository.UserRepository;

@Service
public class UserUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user=userRepo.findByUsername(username)
				.orElseThrow(()-> new BadCredentialsException("User Details not found with this username: "+username));

			List<GrantedAuthority> authorities=new ArrayList<>();
			SimpleGrantedAuthority sga=new SimpleGrantedAuthority(user.getRole().toString());
			authorities.add(sga);
			return new User(user.getUsername(), user.getPassword(), authorities);
	}


}
