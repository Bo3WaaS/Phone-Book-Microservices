package com.example.phonebook.contact.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.phonebook.contact.entity.Contact;

public interface ContactService {
	
	@PreAuthorize("@securityService.isTheSameUser(authentication, #contactId) or hasRole('ADMIN')")
	public Contact getContact(Long contactId);
	@PreAuthorize("@securityService.isPhoneBookOwner(authentication, #phoneBookId) or hasRole('ADMIN')")
	public List<Contact> getContactsByPhoneBookId(Long phoneBookId);
	@PreAuthorize("@securityService.isTheSameUser(authentication, #contactId) or hasRole('ADMIN')")
	public Contact editContact(Long contactId, Contact contact);
	@PreAuthorize("hasRole('USER')")
	public Contact addContact(Contact contact);
	@PreAuthorize("@securityService.isTheSameUser(authentication, #contactId) or hasRole('ADMIN')")
	public void deleteContact(Long contactId);

}
