package com.example.phonebook.contact.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.phonebook.contact.entity.Contact;
import com.example.phonebook.contact.exception.ResourceNotFoundException;
import com.example.phonebook.contact.repository.ContactRepository;

public class ContactServiceTest {
	
	@Mock
	ContactRepository contactRepository;
	
	public ContactService contactService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		contactService = new DefaultContactService(contactRepository);
	}

	@Test
	public void getContactTest() throws Exception {
		// given
		Long id = 1L;

		Contact c = new Contact();
		c.setId(1L);
		c.setFirstName("Test Name");
		c.setLastName("LastName");
		c.setPhoneBookId(1L);

		when(contactRepository.findOne(anyLong())).thenReturn(c);

		// when
		Contact returnedPB = contactService.getContact(id);

		// then
		assertEquals(id, returnedPB.getId());
		verify(contactRepository, times(1)).findOne(anyLong());
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void getNonExistingContactTest() throws Exception {
		contactService.getContact(1000L);
	}

	@Test
	public void editContactTest() throws Exception {
		// given
		Contact c = new Contact();
		c.setId(1L);
		c.setFirstName("Test Name");
		c.setLastName("LastName");
		c.setPhoneBookId(1L);

		Contact c2 = new Contact();
		c2.setId(1L);
		c2.setFirstName("Test Name");
		c2.setLastName("Last Name Edited");
		c2.setPhoneBookId(1L);

		when(contactRepository.exists(anyLong())).thenReturn(true);
		when(contactRepository.findOne(anyLong())).thenReturn(c);
		when(contactRepository.save((Contact) any())).thenReturn(c2);

		// when
		Contact returnedPB = contactService.editContact(1L, c);

		// then
		assertEquals(Long.valueOf(1L), returnedPB.getId());
		assertEquals("Last Name Edited", returnedPB.getLastName());
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void editNonExistingContactTest() throws Exception {
		// given
		Contact c = new Contact();
		c.setFirstName("Test Name");
		c.setLastName("LastName");
		c.setPhoneBookId(1L);

		// when
		contactService.editContact(1000L, c);
		
	}

	@Test
	public void addContactTest() throws Exception {
		// given
		Long id = 1L;

		Contact c = new Contact();
		c.setId(1L);
		c.setFirstName("Test Name");
		c.setLastName("LastName");
		c.setPhoneBookId(1L);
		
		when(contactRepository.save((Contact) any())).thenReturn(c);

		// when
		Contact returnedPB = contactService.addContact(new Contact());

		// then
		assertEquals(id, returnedPB.getId());
	}

	@Test
	public void deleteContactTest() throws Exception {
		// given
		Long id = 1L;

		// when
		contactService.deleteContact(id);

	}

}
