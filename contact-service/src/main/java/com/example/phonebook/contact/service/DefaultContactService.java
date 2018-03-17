package com.example.phonebook.contact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.phonebook.contact.entity.Contact;
import com.example.phonebook.contact.exception.BadInputException;
import com.example.phonebook.contact.exception.ResourceNotFoundException;
import com.example.phonebook.contact.repository.ContactRepository;

@Service
public class DefaultContactService implements ContactService {
	
	private ContactRepository contactRepository;

	public DefaultContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public Contact getContact(Long contactId) {
		Contact contact = contactRepository.findOne(contactId);
		if(contact != null){
			return contact;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<Contact> getContactsByPhoneBookId(Long phoneBookId) {
		List<Contact> contacts = contactRepository.findByPhoneBookId(phoneBookId);
		if(contacts != null){
			return contacts;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Contact editContact(Long contactId, Contact contact) {
		if(contactRepository.exists(contactId)){
			if(contact != null){
				Contact existingContact = contactRepository.findOne(contactId);
				contact.setId(contactId);
				contact.setUserId(existingContact.getUserId());
				return contactRepository.save(contact);
			} else {
				throw new BadInputException("Please insert valid contact.");
			}
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public Contact addContact(Contact contact) {
		contact.setId(null);
		return contactRepository.save(contact);
	}

	@Override
	public void deleteContact(Long contactId) {
		contactRepository.delete(contactId);
	}

}
