package com.example.phonebook.phonebook.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.phonebook.phonebook.config.FeignConfiguration;

@FeignClient(name = "api", configuration = FeignConfiguration.class)
public interface UserService {
	
	@GetMapping("/uaa/auth/userId/{username}")
	Long getUserId(@PathVariable("username") String username);

}
