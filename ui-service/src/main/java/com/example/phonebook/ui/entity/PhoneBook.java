package com.example.phonebook.ui.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PhoneBook {

	
	private Long id;
	private String name;
	private Long userId;
	@JsonFormat(pattern="dd:MM:yyyy hh:mm:ss")
	private Date dateCreated;

	public PhoneBook() {
	}

	// remove this later
	public PhoneBook(Long id, String name, Long userId, Date dateCreated) {
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneBook other = (PhoneBook) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PhoneBook [id=" + id + ", name=" + name + ", userId=" + userId + ", dateCreated=" + dateCreated + "]";
	}

}
