package com.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shop.model.Forder;
import com.shop.model.Status;
import com.shop.model.User;

@Controller("fordertAction")
@Scope("prototype")
public class ForderAction extends BaseAction<Forder>{
	/**
	 * ModelDriven背后的机制就是ValueStack(值栈)。
	 * 界面通过：id这样的属性名，就能够被直接赋值给Forder对象，这证明Forder对象正是ValueStack中的一个root对象！
	 */

	//override重写 getModel方法
	@Override
	public Forder getModel() {
		model = (Forder) session.get("foder");
		return model;
	}
	//实现购物车（订单）与购物项（订单项）级联入库功能  
    public String save() {
    	model.setStatus(new Status(1));
    	model.setUser((User) session.get("user"));
    	forderService.save(model);
    	
    	session.put("oidForder", session.get("foder"));//先将原来的购物车信息保存下来，因为后面付款的时候还需要相关信息  
    	session.put("forder", new Forder());//new一个新的空购物车（相当于清空了购物车），还可以方便用户再买
		return "bank";
	}  
}
