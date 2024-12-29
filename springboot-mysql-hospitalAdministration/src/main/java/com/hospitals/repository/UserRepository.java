package com.hospitals.repository;

import com.hospitals.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByUsernameAndPasswordAndRole(String username, String password, String role);
	
	
	
	

	    // Custom query method to find a user by username
	    UserEntity findByUsername(String username);

	    // Optionally, you can also add methods like this:
	    UserEntity findByEmail(String email);
	}


