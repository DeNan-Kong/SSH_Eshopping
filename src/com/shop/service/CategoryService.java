package com.shop.service;
import java.util.List;

import com.shop.model.Category;

public interface CategoryService extends BaseService<Category>{
	//查询类别信息，级联管理员  
    public List<Category> queryJoinAccount(String type,int page,int size); //并实现分页  
    //根据关键字查询总记录数
    public Long getCount(String type);
    //根据ids删除多条记录  
    public void deleteByIds(String ids);
    //根据boelen值查询热点或非热点类别
    public List<Category> queryByHot(boolean hot);
}
