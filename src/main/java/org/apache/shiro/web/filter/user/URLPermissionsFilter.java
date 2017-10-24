package org.apache.shiro.web.filter.user;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

/**
 * 基于 URL 的权限判断过滤器
 * 我们自动根据 URL 产生所谓的权限字符串，这一项在 Shiro 示例中是写在配置文件里面的，默认认为权限不可动态配置
 *
 */
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {
	/**
	 * @param mappedValue 指的是在声明 url 时指定的权限字符串，如 /User/create.do=perms[User:create].
	 * 我们要动态产生这个权限字符串，所以这个配置对我们没用
	 */
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		 return super.isAccessAllowed(request, response, buildPermissions(request));
	}
	
	/**
	 * 根据请求URL产生权限字符串，这里只产生，而比对的事交给Realm
	 * @param request
	 * @return
	 */
	protected String[] buildPermissions(ServletRequest request) {
		String[] perms = new String[1];
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
			// path直接作为权限字符串
		
		String regex = "/(.*?)/(.*)";
		String url = "/user/insert?tt=1&rr=2";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			String controller = "";
			String action = "";
			// System.out.println("wwwwwwww " + matcher.groupCount());
			if (matcher.groupCount() > 0) {
				controller = matcher.group(1);
			}
			if (matcher.groupCount() > 1) {
				action = matcher.group(2);
			}
			if (action.indexOf("?") > -1) {
				action = action.substring(0, action.indexOf("?"));
			}
			perms[0] = controller + ":" + action;
			// System.out.println("controller="+controller+"|action="+action);
		} else {
			perms[0] = path;
		}
		return perms;
	}
}
