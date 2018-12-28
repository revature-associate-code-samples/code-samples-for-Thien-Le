/**
 * 
 */
package com.revature.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import com.revature.dao.DAO;
import com.revature.dao.ReimbursementDao;
import com.revature.pojo.Reimbursement;
/**
 * @author thienle
 *
 */
public class ReimbursementDAOTest {

	DAO<Reimbursement, Integer> dao;
	
	private static Logger log = Logger.getLogger(ReimbursementDAOTest.class);
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		dao = new ReimbursementDao();
		System.out.println("Setting up instance before test");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		List<Reimbursement> reimbs = dao.findAll();
		
		for(Reimbursement r : reimbs) {
			log.debug(r);
		}
		
		assertNotNull(reimbs);
		assertTrue(reimbs.size() > 0);
	}

}
