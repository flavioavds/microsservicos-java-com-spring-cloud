package com.dev.hruser.resources;

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

import com.dev.hruser.entities.User;
import com.dev.hruser.repositories.UserRepository;
import com.dev.hruser.resources.exception.Messagem;
import com.dev.hruser.resources.exception.UnsuportedMathOperationException;
import com.dev.hruser.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = repository.findById(id).get();
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		User obj = repository.findByEmail(email);
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<Object> saveNewUser(@RequestBody User user){
		if(service.existsName(user.getName()))
			throw new UnsuportedMathOperationException("Usuario já cadastrado!");
		if(service.existsEmail(user.getEmail()))
			throw new UnsuportedMathOperationException("Email já cadastrado!");
		service.saveUser(user);
		return new ResponseEntity<>(new Messagem("Cadastro Realizado com Sucesso!"), HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<User>> getAll(){
		List<User> findAll = service.findAllUser();
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

}
