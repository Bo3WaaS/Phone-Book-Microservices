package com.example.phonebook.ui.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Contact {

	private Long id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@Size(min = 8, max = 8)
	private String phone;
	@NotNull
	private Long phoneBookId;
	private Long userId;

	public Contact() {

	}
	
	// remove this later

	public Contact(Long id, String firstName, String lastName, String phone, Long phoneBookId, Long userId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.phoneBookId = phoneBookId;
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getPhoneBookId() {
		return phoneBookId;
	}

	public void setPhoneBookId(Long phoneBookId) {
		this.phoneBookId = phoneBookId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
