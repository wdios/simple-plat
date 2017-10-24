package com.wis.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wis.model.ajax.SysSuccess;
import com.wis.model.page.Pager;
import com.wis.model.page.PagerJsonResponse;
import com.wis.model.user.User;
import com.wis.tookit.sql.SqlHelper;

@Controller
@RequestMapping("/sysUser/*")
public class SysUserController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/view")
    public ModelAndView viewSysMenuList(ServletRequest request, ServletResponse response) {
    	ModelAndView mav = new ModelAndView("admin/sys_user");
        return mav;
    }
    
    @RequestMapping(value = "/list")
    @ResponseBody
    public PagerJsonResponse<User> getSysUserList(Pager pager) {
    	SqlHelper aSqlHelper = new SqlHelper(new User(), "sys_user");
    	String sqlStr = aSqlHelper.getQuerySQLPage("", "", "", 1, 12);
    	List<User> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<User>(User.class));
    	PagerJsonResponse<User> response = new PagerJsonResponse<User>(list, pager);
        return response;
    }
    
    @RequestMapping(value = "/all")
    @ResponseBody
    public List<User> getAllSysUser() {
    	SqlHelper aSqlHelper = new SqlHelper(new User(), "sys_user");
    	String sqlStr = aSqlHelper.getQuerySQLAll("", "", "");
    	List<User> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<User>(User.class));
        return list;
    }
    
    @RequestMapping(value = "/findByUsername")
    @ResponseBody
    public SysSuccess findByUsername(@RequestParam("username") String username) {
    	User aUser = new User();
    	aUser.setUsername(username);
    	
    	SqlHelper aSqlHelper = new SqlHelper(aUser, "sys_user");
    	String sqlStr = aSqlHelper.getQuerySQLAll("", "", "");
    	List<User> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<User>(User.class));

    	if (null != list && list.size() > 0) {
    		return new SysSuccess(false, "用户名称重复！");
    	} else {
    		return new SysSuccess(true, "没有该用户名！");
    	}
    }
    
    @RequestMapping(value="/save")
    @ResponseBody
    public SysSuccess createSysUser(@ModelAttribute("user") User user) {
    	SqlHelper aSqlHelper = new SqlHelper(user, "sys_user");
    	String sqlStr = aSqlHelper.getInsertSQL();
    	
    	int opNum = jdbcTemplate.update(sqlStr, aSqlHelper.getParams());
    	
        if (opNum == 1) {
            return new SysSuccess(true, "创建成功！");
        } else {
            return new SysSuccess(false, "创建失败！");
        }
    }
    
    @RequestMapping(value="/update")
    @ResponseBody
    public SysSuccess updateSysUser(@ModelAttribute("user") User user) {
    	SqlHelper aSqlHelper = new SqlHelper(user, "sys_user");
    	String sqlStr = aSqlHelper.getUpdateSQL();
    	
    	int opNum = jdbcTemplate.update(sqlStr, aSqlHelper.getParams());
    	
    	if (opNum == 1) {
            return new SysSuccess(true, "修改成功！");
        } else {
            return new SysSuccess(false, "修改失败！");
        }
    }
    
    @RequestMapping(value="/updatepassword")
    @ResponseBody
    public SysSuccess updatePassword(@ModelAttribute("user") User user) {
    	SqlHelper aSqlHelper = new SqlHelper(user, "sys_user");
    	String sqlStr = aSqlHelper.getUpdateSQL();
    	
    	int opNum = jdbcTemplate.update(sqlStr, aSqlHelper.getParams());
    	
    	if (opNum == 1) {
            return new SysSuccess(true, "修改成功！");
        } else {
            return new SysSuccess(false, "修改失败！");
        }
    }
    
    @RequestMapping(value="/delete")
    @ResponseBody
    public SysSuccess deleteSysUser(@RequestParam("id") long id) {
    	User aUser = new User();
    	aUser.setId(id);
    	
    	SqlHelper aSqlHelper = new SqlHelper(aUser, "sys_user");
    	String sqlStr = aSqlHelper.getDeleteSQL("", "", "");
    	
    	int opNum = jdbcTemplate.update(sqlStr, aSqlHelper.getParams());
    	
        if (opNum == 1) {
            return new SysSuccess(true, "删除成功！");
        } else {
            return new SysSuccess(false, "删除失败！");
        }
    }
	
}
