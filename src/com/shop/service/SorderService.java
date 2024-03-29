package com.shop.service;

import com.shop.model.Forder;
import com.shop.model.Product;
import com.shop.model.Sorder;

public interface SorderService extends BaseService<Sorder>{
	//添加购物项，返回新的购物车  
    public Forder addSorder(Forder forder, Product product);  
    //把商品数据转化为购物项  
    public Sorder productToSorder(Product product);  
    //根据商品id和数量更新商品数量
    public Forder updateSorder(Sorder sorder, Forder forder);
}
