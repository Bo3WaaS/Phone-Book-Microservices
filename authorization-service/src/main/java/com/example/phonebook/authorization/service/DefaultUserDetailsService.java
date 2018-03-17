package com.example.phonebook.authorization.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.phonebook.authorization.entity.User;
import com.example.phonebook.authorization.repository.UserRepository;

@Service
public class DefaultUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;

	public DefaultUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String givenUsername) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(givenUsername);
		if(user != null && user.getUsername().equals(givenUsername)){
			return user;
		} else {
			throw new UsernameNotFoundException(givenUsername);
		}
	}

}
