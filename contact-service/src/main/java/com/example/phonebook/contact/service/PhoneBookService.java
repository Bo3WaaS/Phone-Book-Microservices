package com.example.phonebook.contact.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.phonebook.contact.config.FeignConfiguration;

@FeignClient(name = "api", configuration = FeignConfiguration.class)
@RibbonClient("phonebooks")
public interface PhoneBookService {
	
	@GetMapping(value="/phonebooks", params={"userId", "byId"})
	List<Long> getPhoneBooksIdsByUserId(@RequestParam("userId") Long userId, @RequestParam("byId") Boolean byId);

}
