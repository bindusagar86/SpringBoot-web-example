package com.bbtech.service;

import org.springframework.stereotype.Service;

import com.bbtech.model.User;
import com.bbtech.model.Widget;
import com.bbtech.repository.UserRepository;
import com.bbtech.repository.WidgetRepository;

@Service
public class WidgetService {

	private final UserRepository userRepo;
	private final WidgetRepository widgetRepo;
	
	public WidgetService(final UserRepository userRepo,final WidgetRepository widgetRepo) {
		this.userRepo=userRepo;
		this.widgetRepo=widgetRepo;
	}
	
	public User findUser(Long id) {
		return userRepo.findById(id).orElse(new User());
	}
	
	public Widget findById(Long id) {
		return widgetRepo.findById(id).orElse(new Widget());
	}
	
	public Widget createOrUpdateWidget(Widget widget) {
		return widgetRepo.save(widget);
	}
	
	public void deleteWidget(Long id) {
		widgetRepo.deleteById(id);
	}
}
