package com.revature.service;


import java.util.List;

import com.revature.dao.BankAccountDao;
import com.revature.dao.DAO;
import com.revature.pojos.BankAccount;

/**
 * Providing DAO service, handling CRUD methods
 * @author thienle
 *
 */
public class BankAccountService {

	static DAO<BankAccount, Integer> accDao = new BankAccountDao();
	
	/**
	 * Returning a BankAccount by id
	 * @param id
	 * @return
	 */
	public BankAccount findById(Integer id) {
		
		BankAccount a = null;
		
		a = accDao.findById(id);
		
		return a;
	}
	
	/**
	 * Updating BankAccount
	 * @param bAccount
	 */
	public void update(BankAccount bAccount) {
		
		BankAccount acc = null;
		acc = accDao.update(bAccount);
	}
	
	/**
	 * Returning a list of BankAccount
	 * @param id
	 * @return
	 */
	public List<BankAccount> findAllAccount(Integer id) {
		
		BankAccountDao account = new BankAccountDao();
		List<BankAccount> lAccounts = account.findAllAccount(id);
		if(lAccounts.size() == 0) return null;
		
		return lAccounts;
	}
	
	/**
	 * Creating new BankAccount
	 * @param obj
	 */
	public void createNewAccount(BankAccount obj) {
		
		accDao.save(obj);
	}
	
}
