package com.example.phonebook.contact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.phonebook.contact.entity.Contact;
import com.example.phonebook.contact.repository.ContactRepository;

@Component
@Profile("dev")
public class Bootstrap implements CommandLineRunner{
	
	@Autowired
	private ContactRepository repo;

	@Override
	public void run(String... arg0) throws Exception {
		Contact c = new Contact();
		c.setPhoneBookId(1L);
		c.setFirstName("Jack");
		c.setLastName("Bob");
		c.setPhone("12345678");
		c.setUserId(2L);

		repo.save(c);
		
		System.out.println("Phonebooks created: " + repo.count());
		
	}

}
