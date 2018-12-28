 package com.revature.service;



import java.util.List;

import com.revature.dao.DAO;
import com.revature.dao.UserDao;
import com.revature.pojo.User;

public class UserService {
	
	private static DAO<User, Integer> userDao = new UserDao();
	
	
	//get all users
	public List<User> getAllUsers() {
		
		List<User> users = userDao.findAll();
		if(users.size() == 0) return null;
		
		return users;
	}
	
	//get by username
//	private User getByUserName(String name) {
//		
//		User u = users.stream()
//				.filter( x -> x.getUsername().equalsIgnoreCase(name))
//				.findAny()
//				.orElse(null);
//		return u;
//	}
	
	//validate password by username
	public User validateUser(String name, String password) {
		
		UserDao uDao = new UserDao();
		User u = uDao.validateUser(name.toLowerCase(), password);
		
		return u;
	}
	
	public User getUserById(Integer id) {
		
		User u = userDao.findById(id);
		
		return u;
	}

}
