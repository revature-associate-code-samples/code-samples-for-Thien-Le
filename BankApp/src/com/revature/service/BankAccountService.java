package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.BankAccountDao;
import com.revature.dao.DAO;
import com.revature.pojos.BankAccount;
import com.revature.pojos.BankUser;
import com.revature.util.ConnectionFactory;

public class BankAccountService {

	static DAO<BankAccount, Integer> accDao = new BankAccountDao();
	
	public BankAccount findById(Integer id) {
		
		BankAccount a = null;
		
		a = accDao.findById(id);
		
		
				
		return a;
	}
	
	public void update(BankAccount bAccount) {
		
		BankAccount acc = null;
		acc = accDao.update(bAccount);
	}
	
	public List<BankAccount> findAllAccount(Integer id) {
		
		BankAccountDao account = new BankAccountDao();
		List<BankAccount> lAccounts = account.findAllAccount(id);
		if(lAccounts.size() == 0) return null;
		
		return lAccounts;
	}
	
	public void createNewAccount(BankAccount obj) {
		
		accDao.save(obj);
	}
	
}
