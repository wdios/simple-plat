package org.apache.shiro.web.filter.authc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.wis.common.Constants;

/**
 * 退出登录过滤器
 */
public class LogoutFilter extends AccessControlFilter {
	
	private String redirectUrl;

    public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		System.out.println("wwwwwwwwwwwwwwww  into logout access.");
		Session session = getSubject(request, response).getSession(false);
        if(session == null) {
            return true;
        }
        // 即是否允许访问，返回 true 表示允许
        return session.getAttribute(Constants.SESSION_FORCE_LOGOUT_KEY) == null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	System.out.println("wwwwwwwwwwwwwwww  into logout access denied.");
        try {
            getSubject(request, response).logout();// 强制退出
        } catch (Exception e) {/*ignore exception*/}

        // 表示访问拒绝时是否自己处理，如果返回 true 表示自己不处理且继续拦截器链执行，返回 false 表示自己已经处理了（比如重定向到另一个页面）。
        // String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        WebUtils.issueRedirect(request, response, redirectUrl);
        return false;
    }
    
}
