package com.dev.hruser.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.hruser.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findById(Long id);
	
	@Query("SELECT obj FROM User obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))")
	Optional<User> findByNameContains(String name);
	
	@Query("SELECT obj FROM User obj WHERE LOWER(obj.email) LIKE LOWER(CONCAT('%',:email,'%'))")
	Optional<User> findByEmailContains(String email);
	
	@Transactional(readOnly = true)
	Optional<User> findByEmail(String email);
	
	boolean existsById(String id);
	boolean existsByName(String name);
	boolean existsByEmail(String email);
	

}
