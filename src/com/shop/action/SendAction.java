package com.shop.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
/**
 * @Description: TODO(��Action�������WEB-INF��JSP��JSP����ת�����ܣ���Action�������κε��߼�)
 * @author С��
 *
 */
@Controller("sendAction")
public class SendAction extends ActionSupport{
	public String execute(){
		return "send";
	}
}
