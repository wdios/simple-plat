package com.wis.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/sysResource/*")
public class SysResourceController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/view")
	public ModelAndView viewSysMenuList(ServletRequest request,ServletResponse response){
		ModelAndView mav = new ModelAndView("admin/sys_resource");
		return mav;
	}
	
}
