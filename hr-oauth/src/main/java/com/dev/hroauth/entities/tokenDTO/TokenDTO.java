package com.dev.hroauth.entities.tokenDTO;

import java.time.LocalDateTime;

public class TokenDTO {
	
	private LocalDateTime date = LocalDateTime.now();
	private String token;
	
	public TokenDTO() {
		super();
	}

	public TokenDTO(String token) {
		super();
		this.token = token;
	}

	public TokenDTO(LocalDateTime date, String token) {
		super();
		this.date = date;
		this.token = token;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
