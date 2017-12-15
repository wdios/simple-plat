package com.wis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wis.data.SysMenuTree;
import com.wis.model.SysMenu;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/index")
	public String adminIndex(ModelMap model) {
	    String sqlStr = "select * from sys_menu ORDER BY parentid,weight desc";
        List<SysMenu> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<SysMenu>(SysMenu.class));
	    model.addAttribute("treeMenuData", SysMenuTree.formatTree(list));
		return "admin/index";
	}
	
}
