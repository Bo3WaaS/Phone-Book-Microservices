package com.example.phonebook.authorization.oauth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Oauth2Test {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void oauth2TokenProvisioningTest() throws Exception {
		this.mockMvc.perform(post("/oauth/token").header("Authorization", "Basic cGhvbmVib29rY2xpZW50OnNlY3JldA==").param("grant_type", "password").param("username", "admin")
				.param("password", "admin").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}
	
	@Test
	public void oauth2TokenProvisioningFailedClientTest() throws Exception {
		this.mockMvc.perform(post("/oauth/token").header("Authorization", "Basic cGhvbmVib29rY2xpZW50OnNlY3JldA1234==").param("grant_type", "password").param("username", "admin")
				.param("password", "admin").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void oauth2TokenFailedUserProvisioningTest() throws Exception {
		this.mockMvc.perform(post("/oauth/token").header("Authorization", "Basic cGhvbmVib29rY2xpZW50OnNlY3JldA==").param("grant_type", "password").param("username", "admin")
				.param("password", "admin2").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().is4xxClientError());
	}

}
