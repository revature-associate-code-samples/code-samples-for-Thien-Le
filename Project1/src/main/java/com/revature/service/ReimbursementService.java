package com.revature.service;

import java.util.List;

import com.revature.dao.DAO;
import com.revature.dao.ReimbursementDao;
import com.revature.pojo.Reimbursement;

/**
 * Service Reimbursement, handling CRUD methods.
 * @author thienle
 *
 */
public class ReimbursementService {

	private static DAO<Reimbursement, Integer> reimDao = new ReimbursementDao();
	
	//get all reimbursements
	public List<Reimbursement> getAllReimbursements() {
		
		List<Reimbursement> reim = reimDao.findAll();
		if(reim.size() == 0) return null;
		
		return reim;
	}
	
	/**
	 * 
	 * @param id
	 * @return a reimbursement
	 */
	public Reimbursement findById(Integer id) {
		Reimbursement reim;
		return reim = reimDao.findById(id);
	}
	
	/**
	 * Saving reimbursement
	 * @param obj
	 * @return
	 */
	public Reimbursement save(Reimbursement obj) {
		
		Reimbursement r = reimDao.save(obj);
		return r;
	}
	
	/**
	 * Query reimbursement based on userId
	 * @param id
	 * @return reimbursement by userId
	 */
	public List<Reimbursement> getReimbursementByUserId(Integer id) {
		ReimbursementDao reDao = new ReimbursementDao();
		List<Reimbursement> reimb = reDao.findByUserId(id);
		
		if(reimb.size() == 0) return null;
		
		return reimb;
	}
	
	/**
	 * Query reimbursement based on statusId
	 * @param id
	 * @return reimbursement by statusId
	 */
	public List<Reimbursement> getReimbursementByStatusId(Integer id) {
		
		ReimbursementDao reDao = new ReimbursementDao();
		List<Reimbursement> reimb = reDao.findByStatusId(id);
		
		if(reimb.size() == 0) return null;
		
		return reimb;
	}
	
	/**
	 * Updating reimbursement
	 * @param obj
	 * @return
	 */
	public Reimbursement updateReimb(Reimbursement obj) {
		Reimbursement r = reimDao.update(obj);
		return r;
	}
}
