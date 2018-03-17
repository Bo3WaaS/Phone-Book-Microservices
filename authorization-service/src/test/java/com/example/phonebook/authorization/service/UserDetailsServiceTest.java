package com.example.phonebook.authorization.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.phonebook.authorization.entity.User;
import com.example.phonebook.authorization.repository.UserRepository;

public class UserDetailsServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	private UserDetailsService userDetailsService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userDetailsService = new DefaultUserDetailsService(userRepository);
	}
	
	@Test
	public void loadByUserNameTest() throws Exception {
		//given
		User user = new User();
		user.setUsername("admin2");
		
		when(userRepository.findByUsername(anyString())).thenReturn(user);
		
		//when
		User returnedUser = (User) userDetailsService.loadUserByUsername("admin2");
		
		//then
		assertNotNull(user);
		assertEquals(user.getUsername(), returnedUser.getUsername());
		verify(userRepository, times(1)).findByUsername(anyString());
	}

}
