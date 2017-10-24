package com.wis.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wis.model.SysMenu;
import com.wis.model.page.Pager;
import com.wis.model.page.PagerJsonResponse;
import com.wis.tookit.sql.SqlHelper;

@Controller
@RequestMapping("/admin/sysMenu/*")
public class SysMenuController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/view")
	public ModelAndView viewSysMenuList(ServletRequest request,ServletResponse response){
		ModelAndView mav = new ModelAndView("admin/sys_menu");
		return mav;
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public PagerJsonResponse<SysMenu> getSysMenuList(Pager  pager){
		SqlHelper sqlHelper = new SqlHelper(new SysMenu(), "sys_menu");
		String sqlStr = sqlHelper.getQuerySQLPage("", "", "", 1, 12);
		List<SysMenu> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<SysMenu>(SysMenu.class));
		PagerJsonResponse<SysMenu> response = new PagerJsonResponse<SysMenu>(list,pager);
		return response;
	}
	
	@RequestMapping(value = "/all")
	@ResponseBody
	public List<SysMenu> getAllSysMenu() {
		String sqlStr = "select * from sys_menu ORDER BY parentid,weight desc";
		List<SysMenu> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<SysMenu>(SysMenu.class));
		return list;
	}
	
	
	
	
	
}
