package com.shop.service.impl;

import org.springframework.stereotype.Service;

import com.shop.model.Forder;
import com.shop.model.Sorder;
import com.shop.service.ForderService;

@Service("forderService")
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements ForderService {

	@Override
	public double cluTotal(Forder forder) {
		double total = 0.0;  
        for(Sorder sorder : forder.getSorders()) {  
            total += sorder.getNumber() * sorder.getPrice();  
        }  
        return total;  
	}

}
