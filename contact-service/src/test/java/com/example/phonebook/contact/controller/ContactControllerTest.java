package com.example.phonebook.contact.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.phonebook.contact.config.ClientConfig;
import com.example.phonebook.contact.entity.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class ContactControllerTest {
	
	@Autowired
	ClientConfig clientConfig;
	
	ResourceOwnerPasswordResourceDetails ropr;
	OAuth2RestTemplate rest;
	
	@Before
	public void setup() throws Exception {
		ResourceOwnerPasswordResourceDetails ropr = new ResourceOwnerPasswordResourceDetails();
		ropr.setAccessTokenUri("http://localhost:9000/oauth/token");
		ropr.setClientId(clientConfig.getId());
		ropr.setClientSecret(clientConfig.getSecret());
		ropr.setGrantType("password");
		ropr.setClientAuthenticationScheme(AuthenticationScheme.header);
		ropr.setUsername("user");
		ropr.setPassword("user");
		ropr.setAuthenticationScheme(AuthenticationScheme.form);
		
		//DefaultOAuth2ClientContext context = new DefaultOAuth2ClientContext();
		rest = new OAuth2RestTemplate(ropr);
		
	}
	
	@Test
	public void getContactTest() throws Exception {
		
		//when
		Contact returnedContact = this.rest.getForObject("http://localhost:8081/1", Contact.class);
		
		//then 
		assertThat(returnedContact).isNotNull();
		assertThat(returnedContact.getFirstName()).isEqualTo("first");
		
	}

}
