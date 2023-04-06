package com.dev.usermonolito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.usermonolito.entities.User;
import com.dev.usermonolito.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAllUser() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> optional = repository.findById(id);
			return optional.orElseThrow(() -> new EntityNotFoundException("Id não encontrado"+ id));
	}

}
