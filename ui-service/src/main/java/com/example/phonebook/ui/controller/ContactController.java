package com.example.phonebook.ui.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.phonebook.ui.entity.Contact;
import com.example.phonebook.ui.entity.PhoneBook;
import com.example.phonebook.ui.security.AuthenticationFacade;
import com.example.phonebook.ui.service.ContactService;
import com.example.phonebook.ui.service.PhoneBookService;
import com.example.phonebook.ui.service.UserService;

import feign.FeignException;

@Controller("/contacts")
public class ContactController {

	private ContactService contactService;
	private PhoneBookService phoneBookService;
	private AuthenticationFacade authenticationFacade;
	private UserService userService;

	public ContactController(ContactService contactService, PhoneBookService phoneBookService,
			AuthenticationFacade authenticationFacade, UserService userService) {
		this.contactService = contactService;
		this.phoneBookService = phoneBookService;
		this.authenticationFacade = authenticationFacade;
		this.userService = userService;
	}

	@GetMapping(params = "phoneBookId")
	public String viewContacts(Model model, @RequestParam Long phoneBookId) {
		model.addAttribute("phonebook",	phoneBookService.getPhoneBook(phoneBookId, authenticationFacade.getAuthenticationHeader()));
		System.out.println("First Line passed");
		List<Contact> contacts = contactService.getContactsByPhoneBook(phoneBookId,	authenticationFacade.getAuthenticationHeader());
		System.out.println("Second line passed");
		model.addAttribute("contacts", contacts);
		System.out.println("Third line passed");
		return "contactView";
	}

	@GetMapping("/contacts/addContact")
	public String addContactForm(Model model) {
		List<PhoneBook> phoneBooks = phoneBookService.getPhoneBooksByUser(
				userService.getUserId(authenticationFacade.getAuthenticationName()),
				authenticationFacade.getAuthenticationHeader());
		model.addAttribute("action", "addContact");
		model.addAttribute("contact", new Contact());
		model.addAttribute("phonebooks", phoneBooks);
		return "contactForm";
	}

	@PostMapping("/contacts/addContact")
	public String addContact(Model model, @ModelAttribute("contact") Contact contact) {
		Contact returnedContact;
		try {
			returnedContact = contactService.addContact(contact, authenticationFacade.getAuthenticationHeader());
		} catch (FeignException e) {
			List<PhoneBook> phoneBooks = phoneBookService.getPhoneBooksByUser(
					userService.getUserId(authenticationFacade.getAuthenticationName()),
					authenticationFacade.getAuthenticationHeader());
			model.addAttribute("action", "addContact");
			model.addAttribute("contact", new Contact());
			model.addAttribute("phonebooks", phoneBooks);
			model.addAttribute("msg", "some of your input is invalid");
			return "contactForm";
		}

		List<Contact> contacts = contactService.getContactsByPhoneBook(returnedContact.getPhoneBookId(),
				authenticationFacade.getAuthenticationHeader());
		model.addAttribute("contacts", contacts);
		model.addAttribute("msg", "Contact has been successfully added");
		model.addAttribute("phonebook", phoneBookService.getPhoneBook(returnedContact.getPhoneBookId(),
				authenticationFacade.getAuthenticationHeader()));
		return "contactView";
	}

	@GetMapping("/contacts/editContact/{contactId}")
	public String editContactForm(Model model, @PathVariable Long contactId) {
		List<PhoneBook> phoneBooks = phoneBookService.getPhoneBooksByUser(
				userService.getUserId(authenticationFacade.getAuthenticationName()),
				authenticationFacade.getAuthenticationHeader());
		model.addAttribute("action", "" + contactId);
		model.addAttribute("contact",
				contactService.getContact(contactId, authenticationFacade.getAuthenticationHeader()));
		model.addAttribute("phonebooks", phoneBooks);
		return "contactForm";
	}

	@PostMapping("/contacts/editContact/{contactId}")
	public String editContact(Model model, @PathVariable Long contactId, @ModelAttribute("contact") Contact contact) {
		Contact returnedContact;
		try {
			returnedContact = contactService.editContact(contact, contactId, authenticationFacade.getAuthenticationHeader());
		} catch (FeignException e) {
			List<PhoneBook> phoneBooks = phoneBookService.getPhoneBooksByUser(
					userService.getUserId(authenticationFacade.getAuthenticationName()),
					authenticationFacade.getAuthenticationHeader());
			model.addAttribute("action", "addContact");
			model.addAttribute("contact", new Contact());
			model.addAttribute("phonebooks", phoneBooks);
			model.addAttribute("msg", "some of your input is invalid");
			return "contactForm";
		}

		List<Contact> contacts = contactService.getContactsByPhoneBook(returnedContact.getPhoneBookId(),
				authenticationFacade.getAuthenticationHeader());
		model.addAttribute("contacts", contacts);
		model.addAttribute("msg", "Contact has been successfully edited");
		model.addAttribute("phonebook", phoneBookService.getPhoneBook(returnedContact.getPhoneBookId(),
				authenticationFacade.getAuthenticationHeader()));
		return "contactView";
	}

	@GetMapping("/contacts/deleteContact/{contactId}")
	public String deletePhoneBook(Model model, @PathVariable Long contactId) {
		Contact returnedContact = contactService.getContact(contactId, authenticationFacade.getAuthenticationHeader());
		contactService.deleteContact(contactId, authenticationFacade.getAuthenticationHeader());
		List<Contact> contacts = contactService.getContactsByPhoneBook(returnedContact.getPhoneBookId(),
				authenticationFacade.getAuthenticationHeader());
		model.addAttribute("contacts", contacts);
		model.addAttribute("msg", "Contact has been successfully deleted");
		model.addAttribute("phonebook", phoneBookService.getPhoneBook(returnedContact.getPhoneBookId(),
				authenticationFacade.getAuthenticationHeader()));
		return "contactView";
	}

}
