package com.wis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/*")
public class ErrorController {
	
	@RequestMapping(value = "/404")
	public String noPage(ModelMap model) {
		return "error/404";
	}
	
	@RequestMapping(value = "/error")
	public String unknownError(ModelMap model) {
		return "error/error";
	}
	
}
