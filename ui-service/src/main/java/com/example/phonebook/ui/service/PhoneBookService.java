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

import com.example.phonebook.ui.entity.PhoneBook;

@FeignClient(name = "api")
public interface PhoneBookService {
	
	@GetMapping("/phonebooks")
	public List<PhoneBook> getPhoneBooksByUser(@RequestParam("userId") Long userId, @RequestHeader("Authorization") String auth);
	
	@GetMapping("/phonebooks/{phoneBookId}")
	public PhoneBook getPhoneBook(@PathVariable("phoneBookId") Long phoneBookId, @RequestHeader("Authorization") String auth);
	
	@PostMapping("/phonebooks")
	public PhoneBook addPhoneBook(@RequestBody PhoneBook phoneBook, @RequestHeader("Authorization") String auth);
	
	@PutMapping("/phonebooks/{phoneBookId}")
	public PhoneBook editPhoneBook(@RequestBody PhoneBook phoneBook, @PathVariable("phoneBookId") Long phoneBookId, @RequestHeader("Authorization") String auth);

	@DeleteMapping("/phonebooks/{phoneBookId}")
	public void deletePhoneBook(@PathVariable("phoneBookId") Long phoneBookId, @RequestHeader("Authorization") String auth);

}
