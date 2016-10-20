package com.shop.service;

import com.shop.model.Forder;

public interface ForderService extends BaseService<Forder> {
	//计算购物总价格  
    public double cluTotal(Forder forder);  
}
