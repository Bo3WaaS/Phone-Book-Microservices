package com.example.phonebook.contact.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.phonebook.contact.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long>{
	
	List<Contact> findByPhoneBookId(Long phoneBookId);

}
