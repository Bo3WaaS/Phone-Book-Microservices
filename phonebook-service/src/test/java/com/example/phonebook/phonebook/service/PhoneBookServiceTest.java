package com.example.phonebook.phonebook.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.phonebook.phonebook.entity.PhoneBook;
import com.example.phonebook.phonebook.exception.ResourceNotFoundException;
import com.example.phonebook.phonebook.repository.PhoneBookRepository;

public class PhoneBookServiceTest {

	@Mock
	public PhoneBookRepository phoneBookRepository;

	public PhoneBookService phoneBookService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		phoneBookService = new DefaultPhoneBookService(phoneBookRepository);
	}

	@Test
	public void getPhoneBookTest() throws Exception {
		// given
		Long id = 1L;

		PhoneBook pb = new PhoneBook();
		pb.setId(1L);
		pb.setName("Test Name");
		pb.setUserId(2L);

		when(phoneBookRepository.findOne(anyLong())).thenReturn(pb);

		// when
		PhoneBook returnedPB = phoneBookService.getPhoneBook(id);

		// then
		assertEquals(id, returnedPB.getId());
		verify(phoneBookRepository, times(1)).findOne(anyLong());
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void getNonExistingPhoneBookTest() throws Exception {
		phoneBookService.getPhoneBook(1000L);
	}

	@Test
	public void getAllPhoneBooksTest() throws Exception {
		// given

		PhoneBook pb = new PhoneBook();
		pb.setId(1L);
		pb.setName("Test Name");
		pb.setUserId(2L);

		PhoneBook pb2 = new PhoneBook();
		pb2.setId(2L);
		pb2.setName("Test Name");
		pb2.setUserId(2L);

		ArrayList<PhoneBook> pbs = new ArrayList<>();
		pbs.add(pb);
		pbs.add(pb2);

		when(phoneBookRepository.findAll()).thenReturn(pbs);

		// when
		List<PhoneBook> returnedPBs = phoneBookService.getAllPhoneBooks();

		// then
		assertEquals(2, returnedPBs.size());
		verify(phoneBookRepository, times(1)).findAll();
	}
	
	@Test
	public void getEmptyAllPhoneBookTest() throws Exception {
		List<PhoneBook> phoneBooks = phoneBookService.getAllPhoneBooks();
		Assertions.assertThat(phoneBooks.size()).isEqualTo(0);
	}

	@Test
	public void editPhoneBookTest() throws Exception {
		// given
		PhoneBook pb = new PhoneBook();
		pb.setId(1L);
		pb.setName("First");
		pb.setUserId(2L);

		PhoneBook pb2 = new PhoneBook();
		pb2.setId(1L);
		pb2.setName("Second");
		pb2.setUserId(2L);

		when(phoneBookRepository.exists(anyLong())).thenReturn(true);
		when(phoneBookRepository.findOne(anyLong())).thenReturn(pb);
		when(phoneBookRepository.save((PhoneBook) any())).thenReturn(pb2);

		// when
		PhoneBook returnedPB = phoneBookService.editPhoneBook(1L, pb);

		// then
		assertEquals(Long.valueOf(1L), returnedPB.getId());
		assertEquals("Second", returnedPB.getName());
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void editNonExistingPhoneBookTest() throws Exception {
		// given
		PhoneBook pb = new PhoneBook();
		pb.setName("First");
		pb.setUserId(2L);

		// when
		phoneBookService.editPhoneBook(1000L, pb);
		
	}

	@Test
	public void addPhoneBookTest() throws Exception {
		// given
		Long id = 1L;

		PhoneBook pb = new PhoneBook();
		pb.setId(1L);
		pb.setName("Test Name");
		pb.setUserId(2L);
		
		when(phoneBookRepository.save((PhoneBook) any())).thenReturn(pb);

		// when
		PhoneBook returnedPB = phoneBookService.addPhoneBook(new PhoneBook());

		// then
		assertEquals(id, returnedPB.getId());
	}

	@Test
	public void deletePhoneBookTest() throws Exception {
		// given
		Long id = 1L;

		// when
		phoneBookService.deletePhoneBook(id);

	}

}
