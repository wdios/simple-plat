package com.wis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    
	@RequestMapping("/demoTree01")
    public String demoTree01(ModelMap model, HttpServletRequest request) {
		return "demo/tree01/index";
    }
	
	@RequestMapping("/demoTree02")
    public String demoTree02(ModelMap model, HttpServletRequest request) {
        return "demo/tree02/index";
    }
	
}
