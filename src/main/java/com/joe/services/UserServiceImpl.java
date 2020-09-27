package com.joe.services;

import com.joe.dao.UserDao;
import com.joe.domian.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl {

	@Autowired
	private UserDao userDao;

	public User getById(Integer id) {
		User user = userDao.getById(id);
		return user;
	}
	

}