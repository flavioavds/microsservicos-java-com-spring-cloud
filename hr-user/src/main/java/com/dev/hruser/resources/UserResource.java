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
import com.dev.hruser.resources.exception.Messagem;
import com.dev.hruser.resources.exception.UnsuportedMathOperationException;
import com.dev.hruser.service.UserService;

@RestController
@RequestMapping(value = "/v1/users")
public class UserResource {
	
	@Autowired
	private UserService service;	
	
	@GetMapping(value = "/all")
	public List<User> getAllUsers(){
		return service.findAllUser();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User getId = service.getUserId(id);
		return ResponseEntity.ok().body(getId);
	}
	
	@GetMapping(value = "/email")
	public ResponseEntity<User> getEmail(@RequestParam String email){
		User getEmail = service.getUserEmail(email);
		return ResponseEntity.ok().body(getEmail);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		User getEmail = service.findByEmail(email);
		return ResponseEntity.ok().body(getEmail);
	}
	
	@GetMapping("/name")
	public ResponseEntity<User> findByName(@RequestParam String name){
		User getName = service.getUserName(name);
		return ResponseEntity.ok().body(getName);
	}
	
	@GetMapping(value = "/search/{email}")
	public boolean findByUserEmail(@PathVariable String email){
		return service.existsEmail(email);
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
}
