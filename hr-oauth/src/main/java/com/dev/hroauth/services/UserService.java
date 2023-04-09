package com.dev.hroauth.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.hroauth.entities.User;
import com.dev.hroauth.entities.tokenDTO.LoginRequest;
import com.dev.hroauth.entities.tokenDTO.TokenDTO;
import com.dev.hroauth.feignclients.UserFeignClient;
import com.dev.hroauth.jwt.JwtProvider;

@Service
public class UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	public List<User> findAll() {
		List<User> user = userFeignClient.getAllUsers();
		return user;
	}
	
	public User findById(Long id) {
		User user = userFeignClient.findById(id).getBody();
		if(user == null) {
			logger.error("Id not found: " + id);
			throw new IllegalArgumentException("Id not found");
		}
		logger.info("Id found: " + id);
		return user;
	}
	
	public User findByEmailConteins(String email) {
		User user = userFeignClient.getEmail(email).getBody();
		if(user == null) {
			logger.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		logger.info("Email found: " + email);
		return user;
	}	
	
	public User findByEmail(String email) {
		Optional<User> user = userFeignClient.findByEmail(email);
		if(user == null) {
			logger.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		logger.info("Email found: " + email);
		return user.get();
	}
	
	public User findByName(String name) {
		User user = userFeignClient.findByName(name).getBody();
		if(user == null) {
			logger.error("Nome not found: " + name);
			throw new IllegalArgumentException("Nome not found");
		}
		logger.info("Nome found: " + name);
		return user;
	}
	
	public User saveUser(User user) {
		return userFeignClient.saveNewUser(user).getBody();
	}
	
	public TokenDTO login(LoginRequest login) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);
		
		return new TokenDTO(LocalDateTime.now(), token);
	}

}
