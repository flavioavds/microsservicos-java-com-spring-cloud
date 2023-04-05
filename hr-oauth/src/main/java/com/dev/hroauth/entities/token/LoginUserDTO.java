package com.dev.hroauth.entities.token;

public class LoginUserDTO {
	
	private String email;
	private String password;
	
	public LoginUserDTO() {
		super();
	}

	public LoginUserDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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

}
