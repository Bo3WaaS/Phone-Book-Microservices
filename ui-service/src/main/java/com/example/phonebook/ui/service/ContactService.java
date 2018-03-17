package com.example.phonebook.ui.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.phonebook.ui.entity.Contact;

@FeignClient(name="api")
public interface ContactService {
	
	@GetMapping("/contacts")
	public List<Contact> getContactsByPhoneBook(@RequestParam("phoneBookId") Long phoneBookId, @RequestHeader("Authorization") String auth);
	
	@GetMapping("/contacts/{contactId}")
	public Contact getContact(@PathVariable("contactId") Long contactId, @RequestHeader("Authorization") String auth);
	
	@PostMapping("/contacts")
	public Contact addContact(@RequestBody Contact contact, @RequestHeader("Authorization") String auth);
	
	@PutMapping("/contacts/{contactId}")
	public Contact editContact(@RequestBody Contact contact, @PathVariable("contactId") Long contactId, @RequestHeader("Authorization") String auth);

	@DeleteMapping("/contacts/{contactId}")
	public void deleteContact(@PathVariable("contactId") Long contactId, @RequestHeader("Authorization") String auth);

}
