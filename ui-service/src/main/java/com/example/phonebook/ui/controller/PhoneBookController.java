package com.example.phonebook.ui.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.phonebook.ui.entity.PhoneBook;
import com.example.phonebook.ui.security.AuthenticationFacade;
import com.example.phonebook.ui.service.PhoneBookService;
import com.example.phonebook.ui.service.UserService;

@Controller
public class PhoneBookController {
	
	private PhoneBookService phoneBookService;
	private UserService userService;
	private AuthenticationFacade authenticationFacade; 

	public PhoneBookController(PhoneBookService phoneBookService, UserService userService,
			AuthenticationFacade authenticationFacade) {
		this.phoneBookService = phoneBookService;
		this.userService = userService;
		this.authenticationFacade = authenticationFacade;
	}

	@GetMapping("/")
	public String indexPage(Model model){
		List<PhoneBook> phoneBooks = phoneBookService.getPhoneBooksByUser(userService.getUserId(authenticationFacade.getAuthenticationName()), authenticationFacade.getAuthenticationHeader());
		model.addAttribute("phonebooks", phoneBooks);
		return "phonebookView";
	}
	
	@GetMapping("/addPhoneBook")
	public String addPhoneBookForm(Model model){
		model.addAttribute("action", "addPhoneBook");
		model.addAttribute("phonebook", new PhoneBook());
		return "phonebookForm";
	}
	
	@PostMapping("/addPhoneBook")
	public String addPhoneBook(Model model, @ModelAttribute("phonebook") PhoneBook phoneBook){
		phoneBookService.addPhoneBook(phoneBook, authenticationFacade.getAuthenticationHeader());
		List<PhoneBook> phoneBooks = phoneBookService.getPhoneBooksByUser(userService.getUserId(authenticationFacade.getAuthenticationName()), authenticationFacade.getAuthenticationHeader());
		model.addAttribute("phonebooks", phoneBooks);
		model.addAttribute("msg", "Phone Book has been successfully added");
		return "phonebookView";
	}
	
	@GetMapping("/editPhoneBook/{phoneBookId}")
	public String editPhoneBookForm(Model model, @PathVariable Long phoneBookId){
		model.addAttribute("action", "" + phoneBookId);
		model.addAttribute("phonebook", phoneBookService.getPhoneBook(phoneBookId, authenticationFacade.getAuthenticationHeader()));
		return "phonebookForm";
	}
	
	@PostMapping("/editPhoneBook/{phoneBookId}")
	public String editPhoneBook(Model model, @PathVariable Long phoneBookId, @ModelAttribute("phonebook") PhoneBook phoneBook){
		phoneBookService.editPhoneBook(phoneBook, phoneBookId, authenticationFacade.getAuthenticationHeader());
		List<PhoneBook> phoneBooks = phoneBookService.getPhoneBooksByUser(userService.getUserId(authenticationFacade.getAuthenticationName()), authenticationFacade.getAuthenticationHeader());
		model.addAttribute("phonebooks", phoneBooks);
		model.addAttribute("msg", "Phone Book has been successfully edited");
		return "phonebookView";
	}
	
	@GetMapping("/deletePhoneBook/{phoneBookId}")
	public String deletePhoneBook(Model model, @PathVariable Long phoneBookId){
		phoneBookService.deletePhoneBook(phoneBookId, authenticationFacade.getAuthenticationHeader());
		List<PhoneBook> phoneBooks = phoneBookService.getPhoneBooksByUser(userService.getUserId(authenticationFacade.getAuthenticationName()), authenticationFacade.getAuthenticationHeader());
		model.addAttribute("phonebooks", phoneBooks);
		model.addAttribute("msg", "Phone Book has been successfully deleted");
		return "phonebookView";
	}

}
