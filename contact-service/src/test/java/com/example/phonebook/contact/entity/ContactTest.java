package com.example.phonebook.contact.entity;

import static org.junit.Assert.assertNotEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class ContactTest {
	
	private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	
	@Test
	public void contactTest() {
		Contact c = new Contact();
		c.setPhone("1");
		Set<ConstraintViolation<Contact>> violations = validator.validate(c);
		assertNotEquals(0, violations.size());
	}

}
