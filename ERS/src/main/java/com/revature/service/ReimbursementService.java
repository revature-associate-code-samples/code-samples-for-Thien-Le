package com.revature.service;



import java.util.List;

import com.revature.dao.DAO;
import com.revature.dao.ReimbursementDao;
import com.revature.pojo.Reimbursement;

public class ReimbursementService {

	private static DAO<Reimbursement, Integer> reimDao = new ReimbursementDao();
	
	//get all users
	public List<Reimbursement> getAllReimbursements() {
		
		List<Reimbursement> reim = reimDao.findAll();
		if(reim.size() == 0) return null;
		
		return reim;
	}
	
	public Reimbursement findById(Integer id) {
		Reimbursement reim;
		
		return reim = reimDao.findById(id);
	}
	
	public Reimbursement save(Reimbursement obj) {
		
		Reimbursement r = reimDao.save(obj);
		return r;
	}
	
	public List<Reimbursement> getReimbursementByUserId(Integer id) {
		ReimbursementDao reDao = new ReimbursementDao();
		List<Reimbursement> reimb = reDao.findByUserId(id);
		
		if(reimb.size() == 0) return null;
		
		return reimb;
	}
	
	public List<Reimbursement> getReimbursementByStatusId(Integer id) {
		
		ReimbursementDao reDao = new ReimbursementDao();
		List<Reimbursement> reimb = reDao.findByStatusId(id);
		
		if(reimb.size() == 0) return null;
		
		return reimb;
	}
	
	public Reimbursement updateReimb(Reimbursement obj) {
		Reimbursement r = reimDao.update(obj);
		return r;
	}
}
