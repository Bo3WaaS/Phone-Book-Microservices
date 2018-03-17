package com.example.phonebook.ui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import feign.FeignException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(FeignException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionPOJO feignExceptionHandler(FeignException ex){
		ExceptionPOJO pojo = new ExceptionPOJO(500, "Our server is facing some issues, please try later :)");
		return pojo;
	}

}
