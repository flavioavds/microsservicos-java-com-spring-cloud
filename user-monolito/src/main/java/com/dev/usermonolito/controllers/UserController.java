package com.dev.usermonolito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.usermonolito.entities.User;
import com.dev.usermonolito.service.UserService;

@RestController
@RequestMapping("v1/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/all")
	public List<User> getAllUser(){
		System.out.println(service.findAllUser());
		return service.findAllUser();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}

}
