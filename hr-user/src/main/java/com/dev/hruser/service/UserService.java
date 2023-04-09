package com.dev.hruser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.hruser.entities.User;
import com.dev.hruser.entities.enums.RoleList;
import com.dev.hruser.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.addRoles(RoleList.USER);
		return repository.save(user);
	}
	
	public List<User> findAllUser(){
		return repository.findAll();
	}
	
	public User getUserId(Long id) {
		Optional<User> getId =  repository.findById(id);
		return getId.orElseThrow(() -> new EntityNotFoundException("Id não Encontrado " + getId));
	}
	
	public User getUserName(String name) {
		Optional<User> getName =  repository.findByNameContains(name);
		return getName.orElseThrow(() -> new EntityNotFoundException("Nome não Encontrado " + getName));
	}
	
	public User getUserEmail(String email) {
		Optional<User> getEmail =  repository.findByEmailContains(email);
		return getEmail.orElseThrow(() -> new EntityNotFoundException("Email não Encontrado " + getEmail));
	}
	
	public User findByEmail(String email) {
		Optional<User> getEmail =  repository.findByEmail(email);
		return getEmail.orElseThrow(() -> new EntityNotFoundException("Email não Encontrado " + getEmail));
	}
	
	public User updateUser(User user, Long id) {
		User userOriginal = this.getUserId(id);
		user.setId(userOriginal.getId());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}
	
	public String deleteUser(Long id) {
		repository.deleteById(id);
		return id + "Usuario Deletado com sucesso";
	}
	
	public boolean existsName(String name) {
		return repository.existsByName(name);
	}	
	
	public boolean existsEmail(String email) {
		return repository.existsByEmail(email);
	}
	

}
