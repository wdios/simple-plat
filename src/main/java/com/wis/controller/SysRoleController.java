package com.wis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wis.model.SysRole;
import com.wis.model.ajax.SysSuccess;
import com.wis.model.page.Pager;
import com.wis.model.page.PagerJsonResponse;
import com.wis.model.user.User;
import com.wis.tookit.sql.SqlHelper;


@Controller
@RequestMapping("/sysRole/*")
public class SysRoleController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@RequestMapping(value = "/view")
	public ModelAndView viewSysMenuList(){
		ModelAndView mav = new ModelAndView("admin/sys_role");
		return mav;
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public PagerJsonResponse<SysRole> getSysRoleList(Pager pager){
		SqlHelper sqlHelper = new SqlHelper(new SysRole(), "sys_role");
		String sqlStr = sqlHelper.getQuerySQLPage("", "", "", 1, 12);
		List<SysRole> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<SysRole>(SysRole.class));
		PagerJsonResponse<SysRole> response = new PagerJsonResponse<SysRole>(list, pager);
		return response;
	}
	
	@RequestMapping(value = "/all")
	@ResponseBody
	public List<SysRole> getAllSysRole(){
		SqlHelper sqlHelper = new SqlHelper(new SysRole(), "sys_role");
		String sqlStr = sqlHelper.getQuerySQLAll("", "", "");
		List<SysRole> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<SysRole>(SysRole.class));
		return list;
	}
	
	@RequestMapping(value = "/getuserroles")
	@ResponseBody
	public String getUserRoles(@RequestParam String userName){
		SqlHelper sqlHelper = new SqlHelper(new SysRole(), "sys_role");
		/*此处涉及多表关联查询*/
		
		return "";
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public SysSuccess createSysRole(@ModelAttribute("sysRole") SysRole sysRole){
		SqlHelper sqlHelper = new SqlHelper(sysRole, "sys_role");
		String sqlStr = sqlHelper.getInsertSQL();
		int opNum = jdbcTemplate.update(sqlStr,sqlHelper.getParams());
		if(opNum == 1){
			return new SysSuccess(true, "创建成功！");
		}else{
			return new SysSuccess(false, "创建失败！ ");
		}
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public SysSuccess updateSysUser(@ModelAttribute("sysRole") SysRole sysRole){
		SqlHelper sqlHelper = new SqlHelper(sysRole , "sys_role");
		String sqlStr = sqlHelper.getUpdateSQL();
		int opNum = jdbcTemplate.update(sqlStr,sqlHelper.getParams());
		if(opNum == 1){
			return new SysSuccess(true,"修改成功！");
		}else{
			return new SysSuccess(false,"修改失败！");
		}
	
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public SysSuccess deleteSysRole(@RequestParam("id") long id){
		SysRole sysRole = new SysRole();
		sysRole.setId(id);
		
		SqlHelper sqlHelper = new SqlHelper(sysRole, "sys_role");
		String sqlStr = sqlHelper.getDeleteSQL("", "", "");
		int opNum = jdbcTemplate.update(sqlStr,sqlHelper.getParams());
		if(opNum == 1){
			return new SysSuccess(true,"删除成功！");
		}else{
			return new SysSuccess(false, "删除失败！");
		}
		
	}
	
	@RequestMapping("/getallus")
	@ResponseBody
	public SysSuccess getAllUs(@RequestParam long roleId){
		User aUser = new User();
		aUser.setId(roleId);
		SqlHelper sqlHelper = new SqlHelper(aUser, "sys_user");
		String sqlStr = sqlHelper.getQuerySQLAll("", "", "");
		List<User> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<User>(User.class)); 
		if(null != list && list.size() > 0){
			return new SysSuccess(false, "该用户所有用的角色！");
		}else{
			return new SysSuccess(true, "没有给该用户名分配角色！");
		}
		
	}
	
	
	
	
	
	
	

}
