package com.shop.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.Category;
import com.shop.service.CategoryService;


/**
 * @Description TODO(����Spring��ע����ԣ�����֧��Spring3.1������)
 * @author С��
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml") 
public class SSH_Junit {	
     
	@Resource  
    private CategoryService categoryService;  
     
  
 /*   @Test //����Hibernate�Ŀ�����������Ϊû�����ϣ�����ֱ��new
    public void hibernate(){
    	CategoryService categoryService = new CategoryServiceImpl();
    	Category category = new Category("��ͯ����",true);
    	categoryService.save(category);
    }*/
/*   @Test //����Hibernate��Spring���Ϻ� 
    public void hibernateAndSpring(){
    	categoryService.update(new Category()); //categoryServiceͨ��Spring������ע�������  
    }*/
   @Test  
    public void testQueryJoinAccount() {  
       for(Category c : categoryService.queryJoinAccount("",1,2)) {  //��ʾ��һҳ��ÿҳ2������  
           
            System.out.println(c +"," + c.getAccount().getPass());  
       }
      
   } 
}
