package com.dev.usermonolito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.usermonolito.entities.User;
import com.dev.usermonolito.exceptions.AttributeException;
import com.dev.usermonolito.payload.request.LoginRequest;
import com.dev.usermonolito.payload.request.TokenDTO;
import com.dev.usermonolito.payload.response.MessageResponse;
import com.dev.usermonolito.security.services.UserServicesSecurity;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("v1/api/auth")
public class AuthController {
	
	@Autowired
	UserServicesSecurity service;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> authenticateUser(@RequestBody LoginRequest login) throws AttributeException{
		TokenDTO jwtTokenDTO = service.login(login);
		return ResponseEntity.ok(jwtTokenDTO);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody User user){
				service.saveUser(user);
		return new ResponseEntity<>(new MessageResponse("User registered successully!"), HttpStatus.CREATED);
		
	}
}
