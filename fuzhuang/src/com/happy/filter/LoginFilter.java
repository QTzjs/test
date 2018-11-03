   package com.happy.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginFilter implements Filter {
	Enumeration param;

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		param = filterConfig.getInitParameterNames();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		// 获得客户端的IP地址
		//Contact.IP = servletRequest.getRemoteAddr() + "";
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		// //System.out.println(path);

		// 从session里用户信息
		String isLogin = (String) session.getAttribute("isLogin");

		// 登陆页面无需过滤
		if (path.indexOf("login.jsp") > -1
			|| path.indexOf("branduserbrandlist.actioin") > -1
			|| path.indexOf("/weixin/") > -1
			|| path.indexOf("/loginlogin.action") > -1){
		
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		// 判断如果没有取到用户信息,就跳转到登陆页面
		if (isLogin == null || "".equals(isLogin)) {
			// 跳转到登陆页面
			StringBuffer fileURL = servletRequest.getRequestURL();
			if (fileURL.indexOf(".css") > 0 || fileURL.indexOf(".png") > 0
					|| fileURL.indexOf(".jpg") > 0
					|| fileURL.indexOf(".bmp") > 0
					|| fileURL.indexOf(".gif") > 0
					|| fileURL.indexOf(".apk") > 0
					|| fileURL.indexOf(".ipa") > 0
					|| fileURL.indexOf(".woff") > 0
					|| fileURL.indexOf(".ttf") > 0
					|| fileURL.indexOf("image.jsp") > 0) {
				chain.doFilter(servletRequest, servletResponse);
				return;
			} else {
				String contextPath = servletRequest.getContextPath();
				servletResponse.sendRedirect(contextPath + "/jsp/login.jsp");
			}
		} else {
			// 已经登陆,继续此次请求
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}