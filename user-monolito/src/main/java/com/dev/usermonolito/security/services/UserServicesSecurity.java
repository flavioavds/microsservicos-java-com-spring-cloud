package com.dev.usermonolito.security.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.usermonolito.entities.User;
import com.dev.usermonolito.payload.request.LoginRequest;
import com.dev.usermonolito.payload.request.TokenDTO;
import com.dev.usermonolito.repository.UserRepository;
import com.dev.usermonolito.security.jwt.JwtUtils;

@Service
public class UserServicesSecurity {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);		
	}
	
	public TokenDTO login(LoginRequest login) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateToken(authentication);
		
		return new TokenDTO(LocalDateTime.now(), token);
	}
	
	

}
