package com.example.phonebook.phonebook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.phonebook.phonebook.entity.PhoneBook;
import com.example.phonebook.phonebook.repository.PhoneBookRepository;

@Component
@Profile("dev")
public class Bootstrap implements CommandLineRunner{
	
	@Autowired
	private PhoneBookRepository repo;

	@Override
	public void run(String... arg0) throws Exception {
		PhoneBook pb = new PhoneBook();
		pb.setName("My Amazing Phonebook");
		pb.setUserId(2L);
		
		repo.save(pb);
		
		System.out.println("Phonebooks created: " + repo.count());
		
	}

}
