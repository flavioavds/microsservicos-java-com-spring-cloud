package com.dev.hroauth.entities.tokenDTO;

import java.util.Set;

public class SignupRequest {
	
	private String name;
	private String email;
	private String password;
	private Set<String> roles;
	
	public SignupRequest() {
		super();
	}

	public SignupRequest(String name, String email, String password, Set<String> roles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return roles;
	}

	public void setRole(Set<String> role) {
		this.roles = role;
	}
	
}
