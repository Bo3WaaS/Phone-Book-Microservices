package com.example.phonebook.phonebook.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.phonebook.phonebook.service.UserService;

@Component("customAuditorAware")
public class CustomAuditorAware implements AuditorAware<Long> {
	
	private UserService userService;

    public CustomAuditorAware(UserService userService) {
		this.userService = userService;
	}

	@Override
    public Long getCurrentAuditor() {
    	Long loggedUser;
        try{
        	String loggedName = SecurityContextHolder.getContext().getAuthentication().getName();
        	loggedUser = userService.getUserId(loggedName);
        } catch(NullPointerException e) {
        	return 2L;
        }
        return loggedUser;
    }

}
