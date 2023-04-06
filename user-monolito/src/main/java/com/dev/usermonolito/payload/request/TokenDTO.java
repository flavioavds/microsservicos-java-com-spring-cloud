package com.dev.usermonolito.payload.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
	
	private LocalDateTime date = LocalDateTime.now();
	private String token;
	
	public TokenDTO(String token) {
		this.token = token;
	}
	
	
}
