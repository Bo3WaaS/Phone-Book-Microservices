package com.example.phonebook.contact.security;

import org.springframework.security.core.Authentication;

public interface SecurityAuthorization {
	
	boolean isTheSameUser(Authentication auth, Long contactId);
	boolean isPhoneBookOwner(Authentication auth, Long phoneBookId);

}
