package org.apache.shiro.session.mgt;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import com.wis.model.user.UserOnline;
import com.wis.tookit.IpUtils;

/**
 * 创建自定义的 session，添加一些自定义的数据。
 * 如：用户登录到的系统 ip
 * 用户状态（在线 隐身 强制退出）
 * 当前所在系统等
 */
public class OnlineSessionFactory implements SessionFactory {
	
    @Override
    public Session createSession(SessionContext initData) {
        OnlineSession session = new OnlineSession();
        if (initData != null && initData instanceof WebSessionContext) {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (request != null) {
                session.setHost(IpUtils.getIpAddr(request));
                session.setUserAgent(request.getHeader("User-Agent"));
                session.setSystemHost(request.getLocalAddr() + ":" + request.getLocalPort());
            }
        }
        return session;
    }

    public Session createSession(UserOnline userOnline) {
        return userOnline.getSession();
    }
    
}
