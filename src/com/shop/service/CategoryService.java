package com.shop.service;
import java.util.List;

import com.shop.model.Category;

public interface CategoryService extends BaseService<Category>{
	//��ѯ�����Ϣ����������Ա  
    public List<Category> queryJoinAccount(String type,int page,int size); //��ʵ�ַ�ҳ  
    //���ݹؼ��ֲ�ѯ�ܼ�¼��
    public Long getCount(String type);
    //����idsɾ��������¼  
    public void deleteByIds(String ids);
    //����boelenֵ��ѯ�ȵ����ȵ����
    public List<Category> queryByHot(boolean hot);
}
