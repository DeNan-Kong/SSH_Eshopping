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
 * @author С��
 *��������ʵ��Filter�ӿڣ�����дdoFilter����
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
		//�жϵ�ǰsession�Ƿ����û���Ϣ
		if(req.getSession().getAttribute("user") == null){
			//���浱ǰ�û���ȥ��URL��ַ
			String goURL = req.getServletPath();
			String param = req.getQueryString();//��õ�ַ��Я���Ĳ���  
			if(param != null){
				goURL = goURL + "?" + param;//����ƴ�������ַ+����
			}
			req.getSession().setAttribute("goURL", goURL);
			
			//�Ƿ�����ת��ҳ��
			req.getSession().setAttribute("error","�Ƿ��������¼��");
			res.sendRedirect(req.getContextPath() + "/ulogin.jsp");
		}else{
			//�������һ������������ת������ֱ�ӵ�Ŀ��ҳ��  
            chain.doFilter(request, response);  
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
