package com.dev.usermonolito.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserInfoResponse {
	
	private Long id;
	private String name;
	private String email;
	private List<String> roles;

}
