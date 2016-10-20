package com.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shop.model.Forder;
import com.shop.model.Status;
import com.shop.model.User;

@Controller("fordertAction")
@Scope("prototype")
public class ForderAction extends BaseAction<Forder>{
	/**
	 * ModelDriven����Ļ��ƾ���ValueStack(ֵջ)��
	 * ����ͨ����id�����������������ܹ���ֱ�Ӹ�ֵ��Forder������֤��Forder��������ValueStack�е�һ��root����
	 */

	//override��д getModel����
	@Override
	public Forder getModel() {
		model = (Forder) session.get("foder");
		return model;
	}
	//ʵ�ֹ��ﳵ���������빺��������������⹦��  
    public String save() {
    	model.setStatus(new Status(1));
    	model.setUser((User) session.get("user"));
    	forderService.save(model);
    	
    	session.put("oidForder", session.get("foder"));//�Ƚ�ԭ���Ĺ��ﳵ��Ϣ������������Ϊ���渶���ʱ����Ҫ�����Ϣ  
    	session.put("forder", new Forder());//newһ���µĿչ��ﳵ���൱������˹��ﳵ���������Է����û�����
		return "bank";
	}  
}
