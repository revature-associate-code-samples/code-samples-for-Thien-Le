package com.revature.service;

import java.util.List;

import com.revature.dao.AccountTypeDao;
import com.revature.dao.DAO;
import com.revature.pojos.AccountType;

/**
 * Providing DAO service, serving CRUD methods
 * @author thienle
 *
 */
public class BankAccountTypeService {

	private static DAO<AccountType, Integer> accType = new AccountTypeDao();
	
	/**
	 * Returning a list of AccountType
	 * @return
	 */
	public List<AccountType> findAll() {
		
		List<AccountType> acc = accType.findAll();
		
		if(acc.size()==0) return null;
		return acc;
	}
	
	/**
	 * Return AccountType based on id
	 * @param id
	 * @return
	 */
	public AccountType findById(Integer id) {
		
		AccountType acc = new AccountType();
		
		acc = accType.findById(id);
		
		return acc;
	}

}
