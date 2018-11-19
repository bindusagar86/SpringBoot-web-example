package com.bbtech.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbtech.model.User;
import com.bbtech.model.Widget;
import com.bbtech.repository.UserRepository;
import com.bbtech.repository.WidgetRepository;

@Controller
@RequestMapping("users/{userId}")
public class WidgetController {

	
	private static final Logger log = LoggerFactory.getLogger(WidgetController.class);

	private final WidgetRepository widgetRepo;
	private final UserRepository userRepo;
	
	
	public WidgetController(final WidgetRepository widgetRepo,final UserRepository userRepo) {
		this.widgetRepo = widgetRepo;
		this.userRepo=userRepo;
	}
	
	@ModelAttribute("user")
	public User findUser(@PathVariable Long userId) {
		return userRepo.findById(userId).orElse(new User());
	}
	
//	@InitBinder("user")
//	public void setAllowedFields(WebDataBinder binder) {
//		binder.setDisallowedFields("id");
//	}
	
//	@InitBinder("widget")
//	public void setWidgetAllowedFields(WebDataBinder binder) {
//		binder.setDisallowedFields("id");
//	}
	
	/**
	 * Load the new widget page
	 * @param model
	 * @return
	 * */
	@GetMapping("/widgets/new")
	public String newWidget(User user,ModelMap model) {
		Widget widget = new Widget();
		user.addWidget(widget);
		model.put("widget", widget);
		return "widgetform";
	}
	
	/**
	 * create a new widget
	 * @param widget
	 * @param widget
	 * @return
	 * */
	@PostMapping("/widgets/new")
	public String createWidget(@Valid Widget widget,User user, ModelMap model, BindingResult result) {
		user.addWidget(widget);
		if(result.hasErrors()) {
			model.put("widget", widget);
			return "widgetform";
		}else {
			widgetRepo.save(widget);
			return "redirect:/users/{userId}";
		}
	}
	
	
	@GetMapping("/widgets/{id}/edit")
	public String editWidget(@PathVariable Long id, ModelMap model) {
		Widget widget=this.widgetRepo.findById(id).orElse(new Widget());
		log.info("widget id: "+widget.getId());
		model.put("widget",widget);
		return "widgetform";
	}
	
	/**
	 * Update a widget
	 * @param widget
	 * @return
	 * */
	@PostMapping("/widgets/{id}/edit")
	public String updateWidget(@Valid Widget widget,User user,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			widget.setUser(user);
			model.put("widget", widget);
			return "widgetform";
		}else {
			log.info("widget: "+widget);
//			widget.setId(id);
			user.addWidget(widget);
			this.widgetRepo.save(widget);
			return "redirect:/users/{userId}";
		}
	}
	
	/**
	 * Delete a widget
	 * @param id
	 * @return
	 * */
	@GetMapping("/widget/{id}/delete")
	public String deleteWidget(@PathVariable Long id) {
		System.out.println("==========calling deleteWidget=========="+id);
		widgetRepo.deleteById(id);
		return "redirect:/widgets";
	}
	
	
}
