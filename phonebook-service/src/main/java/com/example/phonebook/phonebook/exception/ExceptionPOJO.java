package com.example.phonebook.phonebook.exception;

public class ExceptionPOJO {
	
	private int statusCode;
	private String message;
	
	public ExceptionPOJO() {
		
	}

	public ExceptionPOJO(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
