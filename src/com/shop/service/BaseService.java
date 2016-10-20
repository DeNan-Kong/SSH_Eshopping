package com.shop.service;

import java.util.List;

public interface BaseService<T> {
	public void save(T t);  
	  
    public void update(T t);  
      
    public void delete(int id);  
      
    public T get(int id);//获取一个
      
    public List<T> query();//获取全部
}
