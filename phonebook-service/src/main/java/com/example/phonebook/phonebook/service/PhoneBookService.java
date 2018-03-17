package com.example.phonebook.phonebook.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.phonebook.phonebook.entity.PhoneBook;

public interface PhoneBookService {
	
	@PreAuthorize("@securityService.isTheSameUser(authentication, #id) or hasRole('ADMIN')")
	PhoneBook getPhoneBook(Long id);
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	List<PhoneBook> getPhoneBooksByUserId(Long userId);
	@PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
	List<Long> getPhoneBooksIdsByUserId(Long userId);
	@PreAuthorize("hasRole('ADMIN')")
	List<PhoneBook> getAllPhoneBooks();
	@PreAuthorize("hasRole('USER')")
	PhoneBook addPhoneBook(PhoneBook phoneBook);
	@PreAuthorize("@securityService.isTheSameUser(authentication, #id) or hasRole('ADMIN')")
	PhoneBook editPhoneBook(Long id, PhoneBook phoneBook);
	@PreAuthorize("@securityService.isTheSameUser(authentication, #id) or hasRole('ADMIN')")
	void deletePhoneBook(Long id);

}
