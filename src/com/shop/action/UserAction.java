package com.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shop.model.User;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	public String login(){
		//��¼�ж�
		model = userService.login(model);
		if(model == null){
			session.put("error","��¼ʧ��!");
			return "login";
		}else{
			session.put("user", model);
			//����session��goURL�Ƿ���ֵ������ҳ�����ת
			if(session.get("goURL") == null){
				return "index";//������ҳ
			}else{
				return "goURL";
			}
		}
	}
}
