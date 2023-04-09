package com.dev.hroauth.feignclients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.hroauth.entities.User;

@Component
@FeignClient(name = "hr-user", path = "/v1/users")
public interface UserFeignClient {
	
	@GetMapping(value = "/all")
	List<User> getAllUsers();
	
	@GetMapping(value = "/{id}")
	ResponseEntity<User> findById(@PathVariable Long id);	
	
	@GetMapping(value = "/email")
	ResponseEntity<User> getEmail(@RequestParam String email);
	
	@GetMapping(value = "/search")
	Optional<User> findByEmail(@RequestParam String email);
	
	@GetMapping(value = "/name")
	ResponseEntity<User> findByName(@RequestParam String name);
	
	@GetMapping(value = "/search/{email}")
	boolean findByUserEmail(@PathVariable String email);
	
	@PostMapping(value = "/register")
	ResponseEntity<User> saveNewUser(@RequestBody User user);

}


