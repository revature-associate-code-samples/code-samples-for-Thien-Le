package com.revature.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.pojos.BankAccount;

public class BankAccountDaoTest {

	BankAccount acc;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		acc = new BankAccount(1, 1, 1, 200.00);
		System.out.println("setup");
		System.out.println(acc);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		
		
		acc.deposit(500.00);
		
		double actualVal = acc.getBalance();
		double expectedVal = 700.00;
		;
		assertEquals(expectedVal, actualVal);
		
	}

}

