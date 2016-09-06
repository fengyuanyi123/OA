package com.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.entity.User;
import com.web.model.UserModel;

import com.web.model.impl.UserModelmpl;

public class LoginFilter implements Filter{

	public void destroy() {
		System.out.println("过滤器初始化了");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
//		if("login".equals("methodName") || "reg".equals("methodName") || "logout".equals("methodName")){
			chain.doFilter(req, resp);
//		}else{
//			User user = (User)req.getSession().getAttribute("loginUser");
//			if(null != user){
//				String uri = req.getServletPath();
//				uri = uri.substring(1);
//				UserModel um = new UserModelmpl();
//				boolean b = um.checkUserMenu(user.getUid(), uri);
//				if(b){
//					chain.doFilter(req, resp);
//				}else{
//					req.setAttribute("noRightError", "对不起,你没有访问此资源的权限!");
//					req.getRequestDispatcher("view/noRightError.jsp").forward(req, resp);
//				}
//			}else{
//				req.setAttribute("loginError", "对不起，请先登录！");
//				resp.getWriter().write("<script type='text/javascript'>window.top.location.href='view/login.jsp';</script>");
//			}
//		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("执行过滤了");
		
	}

}

