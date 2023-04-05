package com.dev.usermonolito.payload.request;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
	
	private String name;
	private String email;
	private Set<String> role;
	private String password;

}
