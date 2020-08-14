package com.onlineschool.demo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.onlineschool.demo.validation.ValidEmail;

public class SchoolUser {

	@NotNull(message="is required")
	@Size(min = 1, message="is required")
	private String username;
	
	@NotNull(message="is required")
	@Size(min = 1, message="is required")
	private String firstName;
	
	@NotNull(message="is required")
	@Size(min = 1, message="is required")
	private String lastName;
	
	@NotNull(message="is required")
	@Size(min = 1, message="is required")
	private String password;
	
	@NotNull(message="is required")
	@Size(min = 1, message="is required")
	private String matchingPassword;
	
	@ValidEmail
	@NotNull(message="is required")
	@Size(min = 1, message="is required")
	private String email;

	public SchoolUser() {
		super();
	}

	// - - - getters and setters - - -
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
