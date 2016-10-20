package com.shop.service;

import java.util.List;

import com.shop.model.Product;

public interface ProductService extends BaseService<Product>{
	//��ѯ��Ʒ��Ϣ���������  
    public List<Product> queryJoinCategory(String name,int page,int size); //��ʵ�ַ�ҳ��ʹ����Ʒ���Ʋ�ѯ  
    //���ݹؼ��ֲ�ѯ�ܼ�¼��
    public Long getCount(String name);
    
    public void deleteByIds(String ids);
    //�����ȵ�����ѯ�Ƽ���Ʒ��������ѯǰ4����
    public List<Product> querByCategoryId(int cid); 
}
