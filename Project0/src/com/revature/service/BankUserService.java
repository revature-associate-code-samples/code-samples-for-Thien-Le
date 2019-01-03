package com.revature.service;

import java.util.List;

import com.revature.dao.BankUserDao;
import com.revature.dao.DAO;
import com.revature.pojos.BankUser;

/**
 * Providing DAO service, handling CRUD methods
 * @author thienle
 *
 */
public class BankUserService {

	private static DAO<BankUser, Integer> userDao = new BankUserDao();
	
	/**
	 * 
	 * @return BankUser list
	 */
	public List<BankUser> getAllUsers() {
		
		List<BankUser> users = userDao.findAll();
		
		if(users.size() == 0) return null;
		return users;
	}
	
	/**
	 * Registering a user
	 * @param obj
	 */
	public void registerUser(BankUser obj) {
		
		userDao.save(obj);
	}
	
	/**
	 * Login purpose
	 * @param username
	 * @param password
	 * @return
	 */
	public BankUser checkUserNamePassword(String username, String password) {
		
		BankUserDao bUDao = new BankUserDao();
		BankUser bUser = bUDao.checkUserNamePass(username, password);
		
		return bUser;
		
	}
	
	/**
	 * Check if userName exists
	 * @param username
	 * @return boolean
	 */
	public boolean checkUserNameExist(String username) {
		
		BankUserDao service = new BankUserDao();
		
		return service.checkUserName(username);
	}
}
