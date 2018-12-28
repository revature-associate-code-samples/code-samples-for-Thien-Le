package com.revature.service;

import java.util.List;

import com.revature.dao.AccountTypeDao;
import com.revature.dao.DAO;
import com.revature.pojos.AccountType;

public class BankAccountTypeService {

	private static DAO<AccountType, Integer> accType = new AccountTypeDao();
	
	public List<AccountType> findAll() {
		
		List<AccountType> acc = accType.findAll();
		
		if(acc.size()==0) return null;
		return acc;
	}
	
	public AccountType findById(Integer id) {
		
		AccountType acc = new AccountType();
		
		acc = accType.findById(id);
		
		return acc;
	}

}
