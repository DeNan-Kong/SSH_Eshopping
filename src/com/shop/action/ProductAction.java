package com.shop.action;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shop.model.Product;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product>{
	public String queryJoinCategory(){
//		System.out.println("name:" + model.getName());
//		System.out.println("page��" + page);  
//      System.out.println("rows��" + rows);
        
        pageMap = new HashMap<String, Object>();
        List<Product> productList = productService.queryJoinCategory(model.getName(), page, rows);
        pageMap.put("rows", productList);
        Long total = productService.getCount(model.getName());
        pageMap.put("total", total);
		return "jsonMap";
	}
	public String deleteByIds(){
		productService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	public void save() throws Exception{
		//fileUpload�����౻��ȡ�ˣ�uploadFile����ֱ�ӽ���һ��fileImage���󣬷����µ�ͼƬ��  
        String pic = fileUpload.uploadFile(fileImage);  
        model.setPic(pic);  
		model.setDate(new Timestamp(System.currentTimeMillis())); //����һ�µ�ǰʱ�䣬��Ϊǰ̨û�а�ʱ���ֶδ������������Լ�����һ�¼���
		System.out.println(model);
		productService.save(model);
	}
	public void update() throws Exception{
		//�����ϴ���ͼƬ
		String pic = fileUpload.uploadFile(fileImage);
		model.setPic(pic);  
		model.setDate(new Timestamp(System.currentTimeMillis())); //����һ�µ�ǰʱ�䣬��Ϊǰ̨û�а�ʱ���ֶδ������������Լ�����һ�¼���
		System.out.println(new Timestamp(System.currentTimeMillis()));
//		System.out.println(model.getXremark());
		productService.update(model);
	}
}
