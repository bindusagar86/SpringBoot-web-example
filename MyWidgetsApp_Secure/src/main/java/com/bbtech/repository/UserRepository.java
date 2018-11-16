package com.bbtech.repository;

import org.springframework.data.repository.CrudRepository;

import com.bbtech.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
}
