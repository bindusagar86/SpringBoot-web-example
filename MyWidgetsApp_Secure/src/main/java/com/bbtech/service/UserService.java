package com.bbtech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bbtech.model.User;
import com.bbtech.model.Widget;
import com.bbtech.repository.UserRepository;
import com.bbtech.repository.WidgetRepository;

@Service
public class UserService {

	private final UserRepository userRepo;
	private final WidgetRepository widgetRepo;
	
	public UserService(final UserRepository userRepo, final WidgetRepository widgetRepo) {
		this.userRepo=userRepo;
		this.widgetRepo=widgetRepo;
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public User findById(Long id) {
		return userRepo.findById(id).orElse(new User());
	}
	
	public User createOrUpdateUser(User user) {
		return userRepo.save(user);
	}
	
	public void deleteUser(User user) {
		userRepo.delete(user);
	}
	
	public List<Widget> findWidgetByUserId(Long userId){
		return widgetRepo.findWidgetByUserId(userId);
	}
}
