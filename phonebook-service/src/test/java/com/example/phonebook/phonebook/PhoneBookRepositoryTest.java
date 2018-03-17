package com.example.phonebook.phonebook;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.phonebook.phonebook.entity.PhoneBook;
import com.example.phonebook.phonebook.repository.PhoneBookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PhoneBookRepositoryTest {
	
	@Autowired
	private PhoneBookRepository phoneBookRepository;
	
	PhoneBook pb = new PhoneBook();
	
	@Before
	public void setup() throws Exception{
		pb.setName("Greatest Phonebook");
		pb.setUserId(2L);
		
		phoneBookRepository.save(pb);
		
	}
	
	@Test
	public void findIdsByUserIdTest() throws Exception {
		ArrayList<Long> returnedList = (ArrayList<Long>) phoneBookRepository.findPhoneBookIdByUserId(2L);
		
		assertThat(returnedList.size()).isGreaterThan(0);
		assertThat(returnedList.get(0)).isEqualTo(2L);
	}
	
	@Test
	public void findByUserIdTest() throws Exception {
		ArrayList<PhoneBook> returnedList = (ArrayList<PhoneBook>) phoneBookRepository.findPhoneBookByUserId(2L);
		
		assertThat(returnedList.size()).isGreaterThan(0);
		assertThat(returnedList.get(0)).isEqualTo(pb);
	}

}
