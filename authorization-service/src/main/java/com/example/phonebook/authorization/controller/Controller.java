package com.example.phonebook.authorization.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.phonebook.authorization.entity.User;

@RestController
public class Controller {
	
	private UserDetailsService userDetailsService;
	
	public Controller(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@GetMapping("/user")
	public Authentication getUser(Authentication user){
		return user;
	}
	
	@GetMapping("/userId/{username}")
	public Long getUserId(@PathVariable String username){
		User user = (User) userDetailsService.loadUserByUsername(username);
		return user.getId();
	}

}
