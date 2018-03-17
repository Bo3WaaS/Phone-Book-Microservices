package com.example.phonebook.phonebook.exception;

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

}
