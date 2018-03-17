package com.example.phonebook.contact.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.phonebook.contact.entity.Contact;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTest {

	@Autowired
	private ContactRepository repository;

	@Test
	public void findByPhoneBookIdTest() throws Exception {

		// given
		Contact c = new Contact();
		c.setPhoneBookId(1L);
		c.setFirstName("Jack");
		c.setLastName("Bob");
		c.setPhone("12345678");

		repository.save(c);

		// when
		Contact returnedContact = repository.findByPhoneBookId(1L).get(0);

		// then
		assertNotNull(returnedContact);
	}

	@Test
	public void findByNonExistingPhoneBookIdTest() throws Exception {

		// given
		Contact c = new Contact();
		c.setPhoneBookId(1L);
		c.setFirstName("Jack");
		c.setLastName("Bob");
		c.setPhone("12345678");

		repository.save(c);

		// when
		Contact returnedContact = repository.findByPhoneBookId(2L).get(0);

		// then
		assertNull(returnedContact);
	}

}
