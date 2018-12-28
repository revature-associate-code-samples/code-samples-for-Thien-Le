package com.revature.service;

import java.util.List;

import com.revature.dao.BankAccountDao;
import com.revature.dao.BankUserDao;
import com.revature.dao.DAO;
import com.revature.pojos.BankAccount;
import com.revature.pojos.BankUser;


public class BankUserService {

	private static DAO<BankUser, Integer> userDao = new BankUserDao();
	
	public List<BankUser> getAllUsers() {
		
		List<BankUser> users = userDao.findAll();
		
		if(users.size() == 0) return null;
		return users;
	}
	
	public void registerUser(BankUser obj) {
		
		
		userDao.save(obj);
	}
	
	public BankUser checkUserNamePassword(String username, String password) {
		
		BankUserDao bUDao = new BankUserDao();
		BankUser bUser = bUDao.checkUserNamePass(username, password);
		
		return bUser;
		
	}
	
	public boolean checkUserNameExist(String username) {
		
		BankUserDao service = new BankUserDao();
		
		return service.checkUserName(username);
	}
}
