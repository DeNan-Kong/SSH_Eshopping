package com.shop.service;

import com.shop.model.Forder;

public interface ForderService extends BaseService<Forder> {
	//���㹺���ܼ۸�  
    public double cluTotal(Forder forder);  
}
