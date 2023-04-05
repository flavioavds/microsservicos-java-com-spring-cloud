package com.dev.hroauth.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.hroauth.entities.User;
import com.dev.hroauth.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		
		try {
			User user = service.findByEmail(email);
			return ResponseEntity.ok(user);
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<User> saveNewUser(@RequestBody User user){
		service.save(user);		 
		return new ResponseEntity<>(HttpStatus.CREATED);
	}	
	
	@GetMapping("/list")
	public ResponseEntity<List<User>> getAll(){
		List<User> findAll = service.findAll();
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

	
}
