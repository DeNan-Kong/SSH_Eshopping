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
		
		//用来存储分页的数据  
		pageMap = new HashMap<String,Object>();
		//根据关键字和分页的参数查询相应的数据。
		List<Category> categoryList = categoryService.queryJoinAccount(model.getType(), page, rows);
		//System.out.println(categoryList.get(1).getAccount().getPass());
		//System.out.println(page);
		pageMap.put("rows", categoryList);
		//根据关键字查询总记录数  
		Long totol = categoryService.getCount(model.getType());		
		pageMap.put("totol", totol);
		return "jsonMap";
	}
	public String deleteByIds(){
		System.out.println(ids);
		categoryService.deleteByIds(ids);
		//如果删除成功就会往下执行，将"true"以流的形式传给前台  
		inputStream = new ByteArrayInputStream("true".getBytes()); //将"true"的字节存到流inputStream中
		
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
