package com.bbtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbtech.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
}
