package com.example.phonebook.contact.exception;

import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionPOJO> notFoundExceptionHandler(ResourceNotFoundException ex) {
		ExceptionPOJO exPojo = new ExceptionPOJO(404, "Resource not found.");
		return ResponseEntity.status(404).body(exPojo);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ExceptionPOJO> numberFormatExceptionHandler(NumberFormatException ex) {
		ExceptionPOJO exPojo = new ExceptionPOJO(404, "Resource not found.");
		return ResponseEntity.status(404).body(exPojo);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionPOJO> httpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
		ExceptionPOJO exPojo = new ExceptionPOJO(405, "Method Not Supported.");
		return ResponseEntity.status(405).body(exPojo);
	}
	
	@ExceptionHandler(BadInputException.class)
	public ResponseEntity<ExceptionPOJO> badInputException(
			BadInputException badInputException) {
		ExceptionPOJO exPojo = new ExceptionPOJO(400, badInputException.getMessage());
		return ResponseEntity.status(400).body(exPojo);
	}
	
	@ExceptionHandler(ZuulRuntimeException.class)
	public ResponseEntity<ExceptionPOJO> zuulRuntimeException(
			ZuulRuntimeException zuulRuntimeException) {
		ExceptionPOJO exPojo = new ExceptionPOJO(400, "Our server is facing some issue, please try again :)");
		return ResponseEntity.status(500).body(exPojo);
	}

}
