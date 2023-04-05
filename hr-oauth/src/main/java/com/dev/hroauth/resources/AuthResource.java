package com.dev.hroauth.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.hroauth.entities.User;
import com.dev.hroauth.entities.token.LoginUserDTO;
import com.dev.hroauth.entities.token.TokenDTO;
import com.dev.hroauth.feignclients.UserFeignClient;
import com.dev.hroauth.jwt.JwtProvider;
import com.dev.hroauth.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService service;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtProvider jwtUtils;
	
	@Autowired
	UserFeignClient userFeignClient;
	
	@PostMapping(value = "/register")
	public ResponseEntity<User> saveNewUser(@RequestBody User user){
		service.save(user);		 
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<TokenDTO> authenticateUser(@RequestBody LoginUserDTO dto){
		TokenDTO jwtTokenDTO = service.login(dto);
		return ResponseEntity.ok(jwtTokenDTO);
	}

}
