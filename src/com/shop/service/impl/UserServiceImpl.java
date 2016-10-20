package com.shop.service.impl;

import org.springframework.stereotype.Service;

import com.shop.model.User;
import com.shop.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Override
	public User login(User user) {
		String hql = "from User u where u.login=:login and u.pass=:pass";
		return (User)getSession().createQuery(hql)
				.setString("login", user.getLogin())
				.setString("pass", user.getPass())
				.uniqueResult();//返回一条记录:总记录数
	}

}
