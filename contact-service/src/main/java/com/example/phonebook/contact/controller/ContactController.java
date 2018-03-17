package com.example.phonebook.contact.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.phonebook.contact.entity.Contact;
import com.example.phonebook.contact.service.ContactService;

@RestController
public class ContactController {
	
	private ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@GetMapping(value="/", params="phoneBookId")
	public List<Contact> getContactsByPhoneBookId(@RequestParam Long phoneBookId){
		return contactService.getContactsByPhoneBookId(phoneBookId);
	}
	
	@GetMapping("/{contactId}")
	public Contact getContact(@PathVariable Long contactId){
		return contactService.getContact(contactId);
	}
	
	@PutMapping("/{contactId}")
	public Contact editContact(@PathVariable Long contactId, @RequestBody Contact contact){
		return contactService.editContact(contactId, contact);
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Contact addContact(@RequestBody Contact contact){
		return contactService.addContact(contact);
	}
	
	@DeleteMapping("/{contactId}")
	public void deleteContact(@PathVariable Long contactId){
		contactService.deleteContact(contactId);
	}

}
