package com.shop.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.shop.service.BaseService;
/**
 *@Description TODO������ģ��ĳ�ȡ�� 
 * @author С��
 *����ģ��ĳ�ȡ������Ӧ��
 * @param <T>
 */

@Service("baseService")
@Lazy(true)
@SuppressWarnings("unchecked")
public class BaseServiceImpl<T> implements BaseService<T>{
	
	private Class clazz; //clazz�д洢�˵�ǰ���������ͣ�������T
	@Resource
    private SessionFactory sessionFactory;  
    
    public BaseServiceImpl() {  
        
        System.out.println("this������ǵ�ǰ���ù��췽���Ķ���" + this);  
        System.out.println("��ȡ��ǰthis����ĸ�����Ϣ" + this.getClass().getSuperclass());  
        System.out.println("��ȡ��ǰthis����ĸ�����Ϣ(����������Ϣ)" + this.getClass().getGenericSuperclass());  
        //�õ����͵Ĳ�������  
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();  
        clazz = (Class)type.getActualTypeArguments()[0];//���ز������顣 
	}
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    } 
      
    protected Session getSession() {  
        //�ӵ�ǰ�̻߳�ȡsession�����û���򴴽�һ���µ�session  
        return sessionFactory.getCurrentSession();
    }
    
	@Override
	public void save(T t) {
		getSession().save(t);		
	}

	@Override
	public void update(T t) {
		getSession().update(t);		
	}

	@Override
	public void delete(int id) {
		System.out.println(clazz.getSimpleName());  
        String hql = "delete " + clazz.getSimpleName() + " as c where c.id=:id";  
        getSession().createQuery(hql) //  
                  .setInteger("id", id) //  
                  .executeUpdate();  		
	}

	
	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);  
	}

	@Override
	public List<T> query() {
		String hql = "from " + clazz.getSimpleName();  
        return getSession().createQuery(hql).list(); 
	}

}
