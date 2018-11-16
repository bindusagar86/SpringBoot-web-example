package com.bbtech.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bbtech.model.Widget;
import com.bbtech.repository.WidgetRepository;

@RestController
public class WidgetRestController {

	private static final Logger LOGGER=LoggerFactory.getLogger(WidgetRestController.class);
	
	@Autowired
	private WidgetRepository widgetRepo;
	
	@GetMapping("/rest/widgets")
	public Iterable<Widget> getWidgets(){
		return widgetRepo.findAll();
	}
	
	@GetMapping("/rest/widget/{id}")
	public ResponseEntity<Widget> getWidget(@PathVariable Long id){
		return widgetRepo.findById(id)
				.map(widget->ResponseEntity.ok().body(widget))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/rest/widget")
	public ResponseEntity<Widget> createWidget(@RequestBody Widget widget){
		LOGGER.info("subimitted Name: "+widget.getName()+" Description: "+widget.getDescription());
		Widget newWidget=widgetRepo.save(widget);
		try {
			return ResponseEntity.created(new URI("/rest/widget/"+newWidget.getId())).body(newWidget);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/rest/widget/{id}")
	public ResponseEntity<Widget> getWidgetById(@RequestBody Widget widget,@PathVariable Long id){
		widget.setId(id);
		return ResponseEntity.ok().body(widgetRepo.save(widget));
	}
	
	@PutMapping("/rest/proper/widget/{id}")
	public ResponseEntity<Widget> updateWidgetProper(@RequestBody Widget widget,@PathVariable Long id,
			@RequestHeader("If-Match") Integer ifmatch){
		Optional<Widget> existingWidget=widgetRepo.findById(Long.valueOf(id));
		if(existingWidget.isPresent()) {
			if(ifmatch == 7) {
				widget.setId(id);
				return ResponseEntity.ok().body(widgetRepo.save(widget));
			}else
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}else
			return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/rest/widget/delete/{id}")
	public ResponseEntity<Object> deleteWidget(@PathVariable Long id) {
		widgetRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
