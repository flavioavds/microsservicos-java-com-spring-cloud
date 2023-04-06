package com.dev.usermonolito.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.usermonolito.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(String id);
	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
	
	boolean existsByName(String name);
	
	boolean existsByEmail(String email);

}
