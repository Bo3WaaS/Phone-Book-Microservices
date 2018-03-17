package com.example.phonebook.phonebook.security;

import org.springframework.security.core.Authentication;

public interface SecurityAuthorization {
	
	boolean isTheSameUser(Authentication auth, Long phoneBookId);

}
