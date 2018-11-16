package com.bbtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbtech.model.Widget;
import com.bbtech.repository.WidgetRepository;

@Controller
@RequestMapping("user/{userId}")
public class WidgetController {

	private final WidgetRepository widgetRepo;
	
	
	public WidgetController(WidgetRepository widgetRepo) {
		this.widgetRepo = widgetRepo;
	}

	/**
	 * Load the new widget page
	 * @param model
	 * @return
	 * */
	@GetMapping("widget/new")
	public String newWidget(Model model) {
		model.addAttribute("widget", new Widget());
		return "widgetform";
	}
	
	/**
	 * create a new widget
	 * @param widget
	 * @param widget
	 * @return
	 * */
	@PostMapping("/widget")
	public String createWidget(@PathVariable Long userId,Widget widget,Model model) {
		widget=widgetRepo.save(widget);
		System.out.println("==========after calling createWidget=========="+widget.getName()+" id: "+widget.getId());
		return "redirect:/widget/"+widget.getId();
	}
	
	/**
	 * Create a widget by id
	 * @param id
	 * @param model
	 * @return
	 * */
	@GetMapping("widget/{id}")
	public String getWidgetById(@PathVariable Long id,Model model) {
		System.out.println("==========calling getWidgetById=========="+widgetRepo.findById(id).get().getName());
		model.addAttribute("widget", widgetRepo.findById(id).orElse(new Widget()));
		return "widget";
	}
	
	/**
	 * Get all widgets
	 * @param model
	 * @return
	 * */
	@GetMapping("/widgets")
	public String getWidgets(Model model) {
		System.out.println("==========calling getWidgets==========");
		model.addAttribute("widgets", widgetRepo.findAll());
		return "widgets";
	}
	
	/**
	 * Load the edit widget page for the specific id
	 * @param id
	 * @param model
	 * @return
	 * */
	@GetMapping("/widget/edit/{id}")
	public String editWidget(@PathVariable Long id, Model model) {
		System.out.println("==========calling editWidget=========="+widgetRepo.findById(id).get().getName());
		model.addAttribute("widget",widgetRepo.findById(id).orElse(new Widget()));
		return "widgetform";
	}
	
	/**
	 * Update a widget
	 * @param widget
	 * @return
	 * */
	@PostMapping("/widget/{id}")
	public String updateWidget(Widget widget) {
//		System.out.println("==========calling updateWidget=========="+widget.getName());
//		Long count= widgetRepo.count();
//		widget.setId(count+1);
		widget=widgetRepo.save(widget);
		return "redirect:/widget/"+widget.getId();
	}
	
	/**
	 * Delete a widget
	 * @param id
	 * @return
	 * */
	@GetMapping("/widget/delete/{id}")
	public String deleteWidget(@PathVariable Long id) {
		System.out.println("==========calling deleteWidget=========="+id);
		widgetRepo.deleteById(id);
		return "redirect:/widgets";
	}
	
	
}
