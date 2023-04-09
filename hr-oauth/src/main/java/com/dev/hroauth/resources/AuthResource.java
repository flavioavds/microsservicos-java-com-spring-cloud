package com.dev.hroauth.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.hroauth.entities.tokenDTO.LoginRequest;
import com.dev.hroauth.entities.tokenDTO.TokenDTO;
import com.dev.hroauth.exception.AttributeException;
import com.dev.hroauth.services.UserService;

@RestController
@RequestMapping("v1/api/auth")
public class AuthResource {
	
	@Autowired
	UserService service;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> login(@RequestBody LoginRequest login) throws AttributeException{
		TokenDTO jwtTokenDTO = service.login(login);
		return ResponseEntity.ok(jwtTokenDTO);
	}
}
