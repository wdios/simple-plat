package com.wis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	@RequestMapping(value = "/")
	public String printWelcome(ModelMap model) {
		return "welcome";
	}
	
	@RequestMapping("/logger")
    public String logger(ModelMap model, HttpServletRequest request) {
        model.addAttribute("message", "logger content.");
		return "logger";
    }
	
}
