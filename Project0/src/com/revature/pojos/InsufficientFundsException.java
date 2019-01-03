package com.revature.pojos;

/**
 * My own exception class
 * @author thienle
 *
 */
public class InsufficientFundsException extends Exception {
	private double amount;
	
	/**
	 * Return the insufficientFund
	 * @param amount
	 */
	public InsufficientFundsException(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
}
