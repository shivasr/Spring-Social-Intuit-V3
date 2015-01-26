package com.app.intuit.api;

import java.io.Serializable;

import com.intuit.ia.connection.User;



/**
 * Model class representing a Intuit user's profile information.
 * 
 * @author Jeffrey Williams
 */

public class IntuitProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String firstName;
	private String lastName;
	private String emailAddress;
	
	public IntuitProfile() {
	}

	public IntuitProfile(User user) {
		name = user.getFirstName() + " " + user.getLastName();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		emailAddress = user.getEmailAddress();
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
}
