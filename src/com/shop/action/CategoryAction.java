package com.shop.action;


import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shop.model.Category;
@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction<Category> {
	    
	public String queryJoinAccount(){
		
		//�����洢��ҳ������  
		pageMap = new HashMap<String,Object>();
		//���ݹؼ��ֺͷ�ҳ�Ĳ�����ѯ��Ӧ�����ݡ�
		List<Category> categoryList = categoryService.queryJoinAccount(model.getType(), page, rows);
		//System.out.println(categoryList.get(1).getAccount().getPass());
		//System.out.println(page);
		pageMap.put("rows", categoryList);
		//���ݹؼ��ֲ�ѯ�ܼ�¼��  
		Long totol = categoryService.getCount(model.getType());		
		pageMap.put("totol", totol);
		return "jsonMap";
	}
	public String deleteByIds(){
		System.out.println(ids);
		categoryService.deleteByIds(ids);
		//���ɾ���ɹ��ͻ�����ִ�У���"true"��������ʽ����ǰ̨  
		inputStream = new ByteArrayInputStream("true".getBytes()); //��"true"���ֽڴ浽��inputStream��
		
		return "stream";
	}
	public void save(){
		System.out.println(model);
		categoryService.save(model);
	}
	public void update(){
		System.out.println(model.getType()+","+model.getAccount().getLogin());  
        categoryService.update(model);
	}
	public String query(){		
		jsonList = categoryService.query();		
//		System.out.println(jsonList.get(0).getType());
		return "jsonList";
	}
}
