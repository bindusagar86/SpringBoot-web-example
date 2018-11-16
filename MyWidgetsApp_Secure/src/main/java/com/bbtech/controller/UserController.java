package com.bbtech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bbtech.model.User;
import com.bbtech.repository.UserRepository;

@Controller
public class UserController {
 
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping(value= {"/","/login"})
	public String login(ModelMap model) {
		User user=new User();
		model.put("user", user);
		return "login";
	}
	
	@PostMapping("/login")
	public String login(User user,ModelMap model) {
		User existedUser=userRepo.findByEmail(user.getEmail());
		String userName=existedUser.getEmail();
		String password=existedUser.getPassword();
		log.info("username: "+userName+" password: "+password);
		model.put("edit", null);
		if(password.equals(user.getPassword())) {
			return "redirect:/user/"+existedUser.getId();
		}else {
			model.put("errorMessage", "Username or pasword incorrect!");
			return "login";
		}
	}
	
	@GetMapping("/users")
	public String getAllUsers(ModelMap model) {
		model.put("users", userRepo.findAll());
		return "users";
	}
	
	@GetMapping("/register")
	public String registerUser(ModelMap model) {
		User user=new User();
		model.put("user", user);
		return "userform";
	}
	
	@PostMapping("/register")
	public String saveUser(User user,ModelMap model) {
		log.info("id: "+user.getId());
		userRepo.save(user);
		return "redirect:/user/"+user.getId();
	}
	
	@GetMapping("/user/{id}")
	public String getUserById(@PathVariable Long id,ModelMap model) {
		User existedUser=userRepo.findById(id).orElse(new User());
		log.info("password: "+existedUser.getPassword());
		
		model.put("user", existedUser);
		return "user";
	}
	
	@GetMapping("/user/edit/{id}")
	public String editUser(@PathVariable Long id,ModelMap model) {
		User user=userRepo.findById(id).orElse(new User());
		log.info("existed User: "+user.getId());
//		user.setPassword(user.getPassword());
		model.put("user", user);
		model.put("edit", "edit");
		return "userform";
	}
	
	@PostMapping("/user/{id}")
	public String updateUser(User user) {
//		Long count=userRepo.count();
//		user.setId(count+1);
		userRepo.save(user);
		return "redirect:/user/"+user.getId();
	}
	
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		userRepo.deleteById(id);
		return "redirect:/users";
	}
	
}
