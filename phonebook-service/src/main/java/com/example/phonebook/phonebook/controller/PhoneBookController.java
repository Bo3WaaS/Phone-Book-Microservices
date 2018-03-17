package com.example.phonebook.phonebook.controller;

import java.util.ArrayList;
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

import com.example.phonebook.phonebook.entity.PhoneBook;
import com.example.phonebook.phonebook.service.PhoneBookService;

@RestController
public class PhoneBookController {

	private PhoneBookService phoneBookService;

	public PhoneBookController(PhoneBookService phoneBookService) {
		this.phoneBookService = phoneBookService;
	}

	@GetMapping("/")
	public List<PhoneBook> getAllPhoneBooks() {
		return phoneBookService.getAllPhoneBooks();
	}

	@GetMapping(value = "/", params = "userId")
	public List<PhoneBook> getPhoneBooksByUserId(@RequestParam(required = true) Long userId) {
		return phoneBookService.getPhoneBooksByUserId(userId);
	}
	
	@GetMapping(value = "/", params = { "userId", "byId" })
	public List<Long> getPhoneBooksIdsByUserId(@RequestParam(required = true) Long userId, @RequestParam(required=true) Boolean byId) {
		if(byId!= null && byId.booleanValue()){
			return phoneBookService.getPhoneBooksIdsByUserId(userId);
		} else {
			return new ArrayList<>();
		}
	}

	@GetMapping("/{phoneBookId}")
	public PhoneBook getPhoneBook(@PathVariable Long phoneBookId) {
		return phoneBookService.getPhoneBook(phoneBookId);
	}

	@PutMapping("/{phoneBookId}")
	public PhoneBook editPhoneBook(@PathVariable Long phoneBookId, @RequestBody PhoneBook phoneBook) {
		return phoneBookService.editPhoneBook(phoneBookId, phoneBook);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public PhoneBook addPhoneBook(@RequestBody PhoneBook phoneBook) {
		return phoneBookService.addPhoneBook(phoneBook);
	}

	@DeleteMapping("/{phoneBookId}")
	public void deletePhoneBook(@PathVariable Long phoneBookId) {
		phoneBookService.deletePhoneBook(phoneBookId);
	}

}
