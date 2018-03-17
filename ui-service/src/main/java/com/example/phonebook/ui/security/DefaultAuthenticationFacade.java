package com.example.phonebook.ui.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationFacade implements AuthenticationFacade {

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

	@Override
	public String getAuthenticationHeader() {
		OAuth2Authentication oauth2 = (OAuth2Authentication) getAuthentication();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oauth2.getDetails();
		return "bearer " + details.getTokenValue();
	}

	@Override
	public String getAuthenticationName() {
		return getAuthentication().getName();
	}
	
}
