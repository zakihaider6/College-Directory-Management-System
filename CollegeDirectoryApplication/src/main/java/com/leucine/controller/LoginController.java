package com.leucine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.modal.Users;
import com.leucine.repository.UserRepository;

@RestController
public class LoginController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/signIn")
	public ResponseEntity<Users> loginHandler(Authentication auth){
		Users user=userRepo.findByUsername(auth.getName()).orElseThrow(()->new BadCredentialsException("Invalid Username or password"));
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}
	
}
