package com.dev.hruser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.hruser.entities.User;
import com.dev.hruser.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}
	
	public boolean existsEmail(String email) {
		return repository.existsByEmail(email);
	}
	
	public boolean existsName(String name) {
		return repository.existsByName(name);
	}
	
	public List<User> findAllUser(){
		return repository.findAll();
	}

}
