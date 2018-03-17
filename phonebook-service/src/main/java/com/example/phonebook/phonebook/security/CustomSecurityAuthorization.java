package com.example.phonebook.phonebook.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.phonebook.phonebook.entity.PhoneBook;
import com.example.phonebook.phonebook.repository.PhoneBookRepository;
import com.example.phonebook.phonebook.service.UserService;

@Component("securityService")
public class CustomSecurityAuthorization implements SecurityAuthorization {

	private UserService userService;
	private PhoneBookRepository phoneBookRepository;

	public CustomSecurityAuthorization(UserService userService, PhoneBookRepository phoneBookRepository) {
		this.userService = userService;
		this.phoneBookRepository = phoneBookRepository;
	}

	@Override
	public boolean isTheSameUser(Authentication auth, Long phoneBookId) {
		try {
			Long currentUserId = userService.getUserId(auth.getName());
			PhoneBook phoneBook = phoneBookRepository.findOne(phoneBookId);
			
			if (phoneBook.getUserId().equals(currentUserId)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
