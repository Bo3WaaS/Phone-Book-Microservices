package com.example.phonebook.phonebook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.phonebook.phonebook.entity.PhoneBook;
import com.example.phonebook.phonebook.exception.BadInputException;
import com.example.phonebook.phonebook.exception.ResourceNotFoundException;
import com.example.phonebook.phonebook.repository.PhoneBookRepository;

@Service
public class DefaultPhoneBookService implements PhoneBookService {

	private PhoneBookRepository phoneBookRepository;

	public DefaultPhoneBookService(PhoneBookRepository phoneBookRepository) {
		this.phoneBookRepository = phoneBookRepository;
	}

	@Override
	public PhoneBook getPhoneBook(Long id) {
		PhoneBook pb = phoneBookRepository.findOne(id);
		if (pb != null) {
			return pb;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<PhoneBook> getAllPhoneBooks() {
		Iterable<PhoneBook> phoneBooks = phoneBookRepository.findAll();
		if (phoneBooks != null) {
			return toList(phoneBooks);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public PhoneBook addPhoneBook(PhoneBook phoneBook) {
		phoneBook.setId(null);
		return phoneBookRepository.save(phoneBook);
	}

	@Override
	public PhoneBook editPhoneBook(Long id, PhoneBook phoneBook) {
		if (phoneBookRepository.exists(id)) {
			if (phoneBook != null) {
				PhoneBook existing = phoneBookRepository.findOne(id);
				phoneBook.setId(id);
				phoneBook.setDateCreated(existing.getDateCreated());
				phoneBook.setUserId(existing.getUserId());
				return phoneBookRepository.save(phoneBook);
			} else {
				throw new BadInputException("Please insert valid contact.");
			}
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public void deletePhoneBook(Long id) {
		phoneBookRepository.delete(id);
	}

	@Override
	public List<Long> getPhoneBooksIdsByUserId(Long userId) {
		Iterable<Long> phoneBookIds = phoneBookRepository.findPhoneBookIdByUserId(userId);
		if (phoneBookIds != null) {
			return toList(phoneBookIds);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<PhoneBook> getPhoneBooksByUserId(Long userId) {
		Iterable<PhoneBook> phoneBooks = phoneBookRepository.findPhoneBookByUserId(userId);
		if (phoneBooks != null) {
			return toList(phoneBooks);
		} else {
			return new ArrayList<>();
		}
	}
	
	private <E> ArrayList<E> toList(Iterable<E> i) {
		ArrayList<E> list = new ArrayList<E>();
		i.forEach(list::add);
		return list;
	}
}
