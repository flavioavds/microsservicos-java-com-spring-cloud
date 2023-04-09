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
import com.dev.hroauth.exception.Messagem;
import com.dev.hroauth.services.UserService;

@RestController
@RequestMapping(value = "/v1/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping(value = "/all")
	public List<User> getAllUsers(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findByEmail(@PathVariable Long id){
		
		try {
			User user = service.findById(id);
			return ResponseEntity.ok(user);
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping(value = "/email")
	public ResponseEntity<User> findByEmailConteins(@RequestParam String email){
		
		try {
			User user = service.findByEmailConteins(email);
			return ResponseEntity.ok(user);
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
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
	
	@GetMapping(value = "/name")
	public ResponseEntity<User> findByName(@RequestParam String name){
		
		try {
			User user = service.findByName(name);
			return ResponseEntity.ok(user);
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<Object> saveNewUser(@RequestBody User user){
		service.saveUser(user);
		return new ResponseEntity<>(new Messagem("Cadastro Realizado com Sucesso!"), HttpStatus.CREATED);
	}
	
	
}
