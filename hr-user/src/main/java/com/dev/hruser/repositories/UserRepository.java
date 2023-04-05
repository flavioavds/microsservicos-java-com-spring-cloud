package com.dev.hruser.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.hruser.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	Optional<User> findById(Long id);
	
	boolean existsByName(String name);
	boolean existsByEmail(String email);
	

}
