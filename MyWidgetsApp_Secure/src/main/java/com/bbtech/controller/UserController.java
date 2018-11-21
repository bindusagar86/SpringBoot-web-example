package com.bbtech.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bbtech.model.User;
import com.bbtech.model.Widget;
import com.bbtech.service.UserService;

@Controller
public class UserController {
 
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;
	
	public UserController(final UserService userService) {
		this.userService=userService;
	}
	
	@InitBinder("user")
	public void setUserAllowedFields(WebDataBinder binder) {
		binder.setDisallowedFields("id");
	}
	
	@GetMapping(value= {"/","/login"})
	public String login(ModelMap model) {
		User user=new User();
		model.put("user", user);
		return "login";
	}
	
	@PostMapping("/login")
	public String processLogin(User user,ModelMap model) {
		User existedUser=userService.findByEmail(user.getEmail());
		if(existedUser !=null) {
			String userName=existedUser.getEmail();
			String password=existedUser.getPassword();
			log.info("username: "+userName+" password: "+password);
			model.put("edit", null);
			if(password.equals(user.getPassword())) {
				return "redirect:/users/"+existedUser.getId();
			}
		}
		model.put("errorMessage", "Username or pasword incorrect!");
		return "login";
	}
	
	@GetMapping("/users")
	public String getAllUsers(ModelMap model) {
		model.put("users", userService.getAllUsers());
		return "users";
	}
	
	@GetMapping("/users/new")
	public String registerUser(ModelMap model) {
		User user=new User();
		model.put("user", user);
		return "userform";
	}
	
	@PostMapping("/users/new")
	public String createUser(@Valid User user, ModelMap model, BindingResult result) {
		log.info("========Creating User==========");
		if(result.hasErrors()) {
			model.put("user", user);
			return "userform";
		}else {
			user=userService.createOrUpdateUser(user);
			return "redirect:/users/"+user.getId();
		}
	}
	
	@GetMapping("users/{id}")
	public String getUser(@PathVariable Long id, ModelMap model) {
		User existUser=userService.findById(id);
		model.put("user", existUser);
		return "user";
	}
	
	@GetMapping("users/{id}/edit")
	public String editUser(@PathVariable Long id, ModelMap model) {
		User existUser=this.userService.findById(id);
		log.info("ID: "+existUser.getId());
		model.put("user", existUser);
		model.put("edit", "edit");
		return "userform";
	}
	
	@PostMapping("users/{id}/edit")
	public String updateUser(@Valid User user,BindingResult result,@PathVariable Long id,ModelMap model) {
		log.info("=========Updating User========");
		if(result.hasErrors()) {
			model.put("user", user);
			return "userform";
		}else {
			user.setId(id);
			userService.createOrUpdateUser(user);
			return "redirect:/users/" + user.getId();
		}
	}
	
	@GetMapping("users/{id}/delete")
	public String deleteUser(@PathVariable Long id) {
		User user=userService.findById(id);
		List<Widget> widgets=userService.findWidgetByUserId(id);
		widgets.forEach(widget->{
			log.info("Removing widget: "+widget);
			user.removeWidget(widget);
			
		});
		userService.deleteUser(user);
		return "redirect:/users";
	}
	
	
}
