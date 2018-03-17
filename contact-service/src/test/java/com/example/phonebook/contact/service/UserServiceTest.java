package com.example.phonebook.contact.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.phonebook.contact.config.ClientConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Mock
	private ClientConfig clientConfig;
	
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		//userService = new DefaultUserService(clientConfig);
	}
	
	@Test
	public void getUserIdTest() {
		//given
		String username = "user";
		
		//when
		Long returnedLong = userService.getUserId(username);
		
		//then
		assertEquals(2L, returnedLong.longValue());
		
	}

}
