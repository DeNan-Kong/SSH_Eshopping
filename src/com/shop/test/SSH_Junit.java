package com.shop.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.Category;
import com.shop.service.CategoryService;


/**
 * @Description TODO(采用Spring的注解调试，仅仅支持Spring3.1及以上)
 * @author 小南
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml") 
public class SSH_Junit {	
     
	@Resource  
    private CategoryService categoryService;  
     
  
 /*   @Test //测试Hibernate的开发环境，因为没有整合，可以直接new
    public void hibernate(){
    	CategoryService categoryService = new CategoryServiceImpl();
    	Category category = new Category("儿童休闲",true);
    	categoryService.save(category);
    }*/
/*   @Test //测试Hibernate和Spring整合后 
    public void hibernateAndSpring(){
    	categoryService.update(new Category()); //categoryService通过Spring从上面注入进来的  
    }*/
   @Test  
    public void testQueryJoinAccount() {  
       for(Category c : categoryService.queryJoinAccount("",1,2)) {  //显示第一页，每页2条数据  
           
            System.out.println(c +"," + c.getAccount().getPass());  
       }
      
   } 
}
