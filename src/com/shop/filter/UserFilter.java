package com.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author 小南
 *过滤器，实现Filter接口，并覆写doFilter方法
 */
public class UserFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//判断当前session是否有用户信息
		if(req.getSession().getAttribute("user") == null){
			//保存当前用户想去的URL地址
			String goURL = req.getServletPath();
			String param = req.getQueryString();//获得地址中携带的参数  
			if(param != null){
				goURL = goURL + "?" + param;//重新拼好请求地址+参数
			}
			req.getSession().setAttribute("goURL", goURL);
			
			//非法请求，转跳页面
			req.getSession().setAttribute("error","非法请求，请登录！");
			res.sendRedirect(req.getContextPath() + "/ulogin.jsp");
		}else{
			//如果有下一个过滤器则跳转，否则直接到目标页面  
            chain.doFilter(request, response);  
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
