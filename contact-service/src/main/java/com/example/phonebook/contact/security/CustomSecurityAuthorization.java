package com.example.phonebook.contact.security;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.phonebook.contact.entity.Contact;
import com.example.phonebook.contact.repository.ContactRepository;
import com.example.phonebook.contact.service.PhoneBookService;
import com.example.phonebook.contact.service.UserService;



@Component("securityService")
public class CustomSecurityAuthorization implements SecurityAuthorization {

	private UserService userService;
	private PhoneBookService phoneBookService;
	private ContactRepository contactRepository;	

	public CustomSecurityAuthorization(UserService userService, PhoneBookService phoneBookService,
			ContactRepository contactRepository) {
		this.userService = userService;
		this.phoneBookService = phoneBookService;
		this.contactRepository = contactRepository;
	}

	@Override
	public boolean isTheSameUser(Authentication auth, Long contactId) {
		try {
			Long currentUserId = userService.getUserId(auth.getName());
			Contact contact = contactRepository.findOne(contactId);
			if (contact.getUserId().equals(currentUserId)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isPhoneBookOwner(Authentication auth, Long phoneBookId) {
		try {
			Long currentUserId = userService.getUserId(auth.getName());
			ArrayList<Long> ids = (ArrayList<Long>) phoneBookService.getPhoneBooksIdsByUserId(currentUserId, true);
			long count = ids.stream().filter(id -> id.equals(phoneBookId)).count();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
