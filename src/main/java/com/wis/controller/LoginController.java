package com.wis.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.wis.model.SysError;
import com.wis.tookit.CusStringUtils;
import com.wis.tookit.jcaptcha.JCaptcha;

@Controller
public class LoginController {
	
	@Autowired
    private CacheManager ehcacheManager;
    /**
     * 登录缓存
     */
    private Cache loginRecordCache;
    
    @PostConstruct
    public void init() {
        loginRecordCache = ehcacheManager.getCache("loginRecordCache");
    }
	
	@RequestMapping(value="/admin/login", method=RequestMethod.GET)
    public String loginGet(SysError sysError, ModelMap model) {
		System.out.println("wwwwwwwwwww --------- login get");
		boolean isError = false;
		if (sysError.getJcaptchaError() == 1) {
			isError = true;
			model.addAttribute("message_login", "验证码输入错误！");
    	}
		model.addAttribute("isError", isError);
		return "admin/login";
	}
	
    @RequestMapping("/admin/logout")
    public String logout(HttpSession session){
        String currentUser = (String)session.getAttribute("currentUser");
        System.out.println("用户[" + currentUser + "]准备登出");
        SecurityUtils.getSubject().logout();
        System.out.println("用户[" + currentUser + "]已登出");
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    }

    @RequestMapping(value="/admin/login", method=RequestMethod.POST)
    public String adminLogin(HttpServletRequest request, ModelMap model) {
    	System.out.println("wwwwwwwwwww --------- login post.");
    	Subject currentUser = SecurityUtils.getSubject();
    	if (currentUser.isAuthenticated()) {
        	return "admin/index";
        }

    	AuthenticationException theException = (AuthenticationException)request.getAttribute("shiroLoginFailure");
    	System.out.println("wwwwwwwwwww --------- theException = " + theException);
    	boolean isError = false;
		if ("UserNotExistsException".equals(theException.getMessage())
				|| "UserPasswordNotMatchException".equals(theException.getMessage()) ) {
			isError = true;
			model.addAttribute("message_login", "用户名或密码输入错误！");
    	} else if ("UserPasswordRetryLimitMaxException".equals(theException.getMessage())) {
    		isError = true;
			model.addAttribute("message_login", "密码输入错误，<br />再次输入错误账户将被锁定30分钟！");
    	} else if ("UserPasswordRetryLimitExceedException".equals(theException.getMessage())) {
    		isError = true;
			model.addAttribute("message_login", "账户被锁定，<br />请30分钟后再尝试！");
    	} else if ("UserBlockedException".equals(theException.getMessage())) {
    		isError = true;
			model.addAttribute("message_login", "账户被封禁，<br />请联系管理员！");
    	} else if ("UnknownException".equals(theException.getMessage())) {
    		isError = true;
			model.addAttribute("message_login", "未知错误，<br />请联系管理员！");
    	}
		model.addAttribute("isError", isError);
    	
    	return "login/login-01";
    }
    
    @RequestMapping(value = "/jcaptchaCheckVicode")
	@ResponseBody
	public Boolean jcaptchaCheckVicode(HttpServletRequest request, String vicode) {
    	if (CusStringUtils.isNotEmpty(vicode)) {
    		return JCaptcha.hasCaptcha(request, vicode);
    	} else {
    		return Boolean.FALSE;
    	}
	}
    
}
