package com.bbtech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorController {

	
	private static final Logger log = LoggerFactory.getLogger(ErrorController.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exception(final Exception ex,final ModelMap model) {
		log.error("Exception during execution of app: ",ex);
		String errorMsg= ex !=null ? ex.getMessage() :"Unknown Error";
		model.put("errorMsg", errorMsg);
		return "error";
	}
}
