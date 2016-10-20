package com.shop.service;

import com.shop.model.Forder;
import com.shop.model.Product;
import com.shop.model.Sorder;

public interface SorderService extends BaseService<Sorder>{
	//��ӹ���������µĹ��ﳵ  
    public Forder addSorder(Forder forder, Product product);  
    //����Ʒ����ת��Ϊ������  
    public Sorder productToSorder(Product product);  
    //������Ʒid������������Ʒ����
    public Forder updateSorder(Sorder sorder, Forder forder);
}
